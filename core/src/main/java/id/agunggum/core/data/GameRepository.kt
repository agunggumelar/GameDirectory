package id.agunggum.core.data

import android.util.Log
import id.agunggum.core.data.source.local.LocalDataSource
import id.agunggum.core.data.source.local.entity.GameEntity
import id.agunggum.core.data.source.remote.RemoteDataSource
import id.agunggum.core.data.source.remote.network.ApiResponse
import id.agunggum.core.data.source.remote.response.GameResponse
import id.agunggum.core.data.source.remote.response.ResultsItem
import id.agunggum.core.domain.model.Game
import id.agunggum.core.domain.repository.IGameRepository
import id.agunggum.core.utils.AppExecutors
import id.agunggum.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    override fun getAllGame(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty()
                //true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(tourismList)
            }
        }.asFlow() as Flow<Resource<List<Game>>>

    override fun getDetailGame(id: Int): Flow<Resource<Game>> =
        object : NetworkBoundResource<Game, GameResponse>() {
            override fun loadFromDB(): Flow<Game?>? {
                return localDataSource.getGameById(id)?.map { gameEntity: GameEntity? ->
                    if (gameEntity == null) {
                        return@map null
                    } else {
                        return@map DataMapper.mapEntityToDomain(gameEntity)
                    }
                }
            }

            override fun shouldFetch(data: Game?): Boolean {
                Log.d("DetailActivity", "repository : ${data?.description == ""}")
                return data?.description == "" || data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getDetailGame(id)

            override suspend fun saveCallResult(data: GameResponse) {
                val gameDetail = DataMapper.mapResponseToEntity(data)
                localDataSource.insertGame(gameDetail)
            }
        }.asFlow() as Flow<Resource<Game>>

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        gameEntity.isFavorite = !gameEntity.isFavorite
        appExecutors.diskIO().execute { localDataSource.editGame(gameEntity) }
    }

    override suspend fun insertGame(game: Game) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.editGame(gameEntity) }
    }
}