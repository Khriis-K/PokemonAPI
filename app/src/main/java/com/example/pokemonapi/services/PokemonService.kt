package dev.bensalcie.retrofitest.services

import com.example.pokemonapi.models.MyPokemon
//import dev.bensalcie.retrofitest.models.MyCountry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{id}")
    fun getAffectedPokemon (@Path("id") id : Int) : Call<MyPokemon>
}