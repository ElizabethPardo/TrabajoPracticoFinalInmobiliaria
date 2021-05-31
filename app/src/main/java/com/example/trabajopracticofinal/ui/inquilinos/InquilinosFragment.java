package com.example.trabajopracticofinal.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.trabajopracticofinal.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import com.example.trabajopracticofinal.modelo.Inquilino;

public class InquilinosFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private InquilinosViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

                ViewModelProviders.of(this).get(InquilinosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inquilinos, container, false);

        inicializar(root);
        return root;
    }

    public  void  inicializar(View v)
    {
        viewPager= v.findViewById(R.id.viewPage2);
        appBarLayout= v.findViewById(R.id.appbar2);
        tabLayout = new TabLayout(getContext());
        appBarLayout.addView(tabLayout);

        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinosViewModel.class);
        vm.getInquilinos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inquilino>>() {
            @Override
            public void onChanged(ArrayList<Inquilino> inquilinos) {

                InquilinosFragment.ViewPageAdapter adapter = new InquilinosFragment.ViewPageAdapter(getParentFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
                int numero=0;
                for(Inquilino inquilino :inquilinos)
                {
                    numero++;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("inquilino",inquilino);
                    InquilinoFragment fragment= new InquilinoFragment();
                    fragment.setArguments(bundle);
                    adapter.addFragment(fragment,"Inquilino "+numero);
                }

                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        vm.cargarInquilinos();
    }

    public class ViewPageAdapter extends FragmentPagerAdapter
    {
        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> titulos= new ArrayList<>();
        private int numero;

        public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position)
        {
            return fragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titulos.get(position);
        }

        public  void  addFragment(Fragment fragment, String titulo)
        {
            fragmentList.add(fragment);
            titulos.add(titulo);
        }
    }
}