package id.agunggum.favorite.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.agunggum.core.ui.GameAdapter
import id.agunggum.favorite.DaggerFavoriteComponent
import id.agunggum.favorite.ViewModelFactory
import id.agunggum.favorite.databinding.FragmentFavoriteBinding
import id.agunggum.gamedirectory.di.FavoriteModuleDependencies
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val favoriteGameAdapter = GameAdapter()

            favoriteGameAdapter.onItemClick = { selectedGame ->
                val uri = Uri.parse("gamedirectory://detail/${selectedGame.id}")
                view.findNavController().navigate(uri)
            }

            favoriteViewModel.favoriteGames.observe(viewLifecycleOwner) { favoriteGames ->
                if (favoriteGames.isEmpty()) {
                    binding.ivNoFavGames.isVisible = true
                    binding.tvNoFavGames.isVisible = true
                    favoriteGameAdapter.setData(favoriteGames)
                } else {
                    binding.ivNoFavGames.isVisible = false
                    binding.tvNoFavGames.isVisible = false
                    favoriteGameAdapter.setData(favoriteGames)
                }
            }

            with(binding.rvFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteGameAdapter
            }
        }

    }

}