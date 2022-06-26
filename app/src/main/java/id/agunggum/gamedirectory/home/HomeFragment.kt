package id.agunggum.gamedirectory.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.agunggum.core.data.Resource
import id.agunggum.core.domain.model.Game
import id.agunggum.core.ui.GameAdapter
import id.agunggum.gamedirectory.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val gameAdapter = GameAdapter()

            gameAdapter.onItemClick = { selectedGame ->
                navToDetail(view,selectedGame)
            }

            homeViewModel.game.observe(viewLifecycleOwner) { game ->
                if (game != null) {
                    when (game) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            gameAdapter.setData(game.data)
                            showLoading(false)
                        }
                        is Resource.Error -> {
                            Toast.makeText(context, "Error ${game.message}", Toast.LENGTH_SHORT)
                                .show()
                            showLoading(false)
                        }
                    }
                }
            }

            with(binding.rvGames) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }

    }

    private fun navToDetail(view: View, selectedGame: Game) {
        val toDetailUser = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        toDetailUser.id = selectedGame.id
        view.findNavController().navigate(toDetailUser)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
        binding.rvGames.isVisible = !state
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}