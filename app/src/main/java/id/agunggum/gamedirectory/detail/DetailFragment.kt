package id.agunggum.gamedirectory.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.agunggum.core.data.Resource
import id.agunggum.core.domain.model.Game
import id.agunggum.gamedirectory.R
import id.agunggum.gamedirectory.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = DetailFragmentArgs.fromBundle(arguments as Bundle).id
        if (gameId != null) {
            detailViewModel.getDetailGame(gameId).observe(viewLifecycleOwner) { detailGame ->
                when (detailGame) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        val dataGame = detailGame.data as Game
                        setFavoriteState(dataGame.isFavorite)
                        populateData(dataGame)
                        showLoading(false)
                        binding.fabFavorite.setOnClickListener {
                            detailViewModel.setFavoriteGame(dataGame)
                            setFavoriteState(dataGame.isFavorite)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(activity, "error : ${detailGame.message}", Toast.LENGTH_SHORT)
                            .show()
                        showLoading(false)
                    }
                }
            }
        }

    }

    private fun setFavoriteState(isFav: Boolean) {
        if (isFav) binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
        else binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_border))

    }

    private fun populateData(game: Game) {
        binding.tvTitle.text = game.name
        binding.tvDescription.text = game.description

        Glide.with(this)
            .load(game.imageUrl)
            .into(binding.ivDetailImage)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
        binding.appBar.isVisible = !state
        binding.nsvDetail.isVisible = !state
    }

}