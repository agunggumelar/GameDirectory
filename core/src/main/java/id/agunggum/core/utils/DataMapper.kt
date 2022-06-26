package id.agunggum.core.utils

import id.agunggum.core.data.source.local.entity.GameEntity
import id.agunggum.core.data.source.remote.response.GameResponse
import id.agunggum.core.data.source.remote.response.ResultsItem
import id.agunggum.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val tourism = GameEntity(
                id = it.id,
                name = it.name,
                description= "",
                released = it.released,
                rating = it.rating,
                imageUrl = it.backgroundImage,
                isFavorite = false
            )
            gameList.add(tourism)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                description= it.description,
                released = it.released,
                rating = it.rating,
                imageUrl = it.imageUrl,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        id = input.id,
        name = input.name,
        description= input.description,
        released = input.released,
        rating = input.rating,
        imageUrl = input.imageUrl,
        isFavorite = input.isFavorite
    )


    fun mapEntityToDomain(input: GameEntity): Game = Game(
        id = input.id,
        name = input.name,
        description = input.description,
        released = input.released,
        imageUrl = input.imageUrl,
        rating = input.rating,
        isFavorite = input.isFavorite
    )

    fun mapResponseToEntity(input: GameResponse): GameEntity {

        return GameEntity(
            id = input.id,
            name = input.name,
            description = input.descriptionRaw,
            released = input.released ?: "No Data",
            imageUrl = input.backgroundImage,
            rating = input.rating,
            isFavorite = false
        )
    }
}