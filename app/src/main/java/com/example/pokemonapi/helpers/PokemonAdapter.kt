import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapi.R
import com.example.pokemonapi.models.MyPokemon
import com.squareup.picasso.Picasso

class PokemonAdapter(val pokemonList: MutableList<MyPokemon>) :RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // which layout are we using to display our items in the recycler view?
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return pokemonList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${pokemonList.size} ")


        return holder.bind(pokemonList[position])

    }
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var imageView = itemView.findViewById<ImageView>(R.id.sprite)
        var pokemonTitle = itemView.findViewById<TextView>(R.id.name)
        var pokemonType = itemView.findViewById<TextView>(R.id.type)

        fun bind(pokemon: MyPokemon) {

            pokemonTitle.text = pokemon.name
            pokemonType.text = pokemon.types.toString()
            Picasso.get().load(pokemon.sprites.front_default).into(imageView)
        }

    }
}