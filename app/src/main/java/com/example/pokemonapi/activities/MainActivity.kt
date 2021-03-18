package com.example.pokemonapi.activities

import PokemonAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.pokemonapi.models.MyPokemon
import com.example.pokemonapi.R
import dev.bensalcie.retrofitest.services.PokemonService
import dev.bensalcie.retrofitest.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val pokemonEntries = 6
    lateinit var adapter: PokemonAdapter
    var pokedex : MutableList<MyPokemon>  = mutableListOf<MyPokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // adapter = PokemonAdapter(pokedex)

        /* pokemon_recycler.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            Log.d(TAG, "list passed in: " + pokedex.toString())
            adapter = this.adapter
        }

        for (i in 1..pokemonEntries) {
            loadPokemon(i)
        } */
        loadPokemon()
    }


    fun loadPokemon() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(PokemonService::class.java)

        // for loop making requestCalls

        for (id in 1..pokemonEntries) {
            val requestCall = destinationService.getAffectedPokemon(id)
            runBlocking {
                Log.d(TAG, "Ran blocking with id: " + id)
                addToPokedex(requestCall)
            }
        }
        Log.d(TAG, "final list passed in: " + pokedex.toString())
        pokemon_recycler.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            // adapter = this.adapter
            adapter = PokemonAdapter(pokedex)
        }
    }

    suspend fun addToPokedex(request : Call<MyPokemon>)
    {
        request.enqueue(object : Callback<MyPokemon> {
            override fun onResponse(
                call: Call<MyPokemon>,
                response: Response<MyPokemon>
            ) {
                val singlePokemon = response.body()!!
                Log.d(TAG, "onResponse: " + singlePokemon)
                // add MyPokemon object to MutableList
                if (response.isSuccessful) {
                    pokedex.add(singlePokemon)
                    Log.d(TAG, "list passed in: " + pokedex.toString())
                    // adapter.pokemonList.add(singlePokemon)
                    // adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<MyPokemon>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
    }
}

/* list of things tried
- load pokemon inside for loop
- create new class to store MutableList
- susoend / async stuffs
- Call List of MyPokemon
 */
