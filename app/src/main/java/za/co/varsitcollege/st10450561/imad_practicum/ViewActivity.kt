package za.co.varsitcollege.st10450561.imad_practicum

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

            val recipes = intent.getStringArrayListExtra("recipes") ?: arrayListOf()
            val categories = intent.getStringArrayListExtra("categories") ?: arrayListOf()
            val ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
            val ingredients = intent.getStringArrayListExtra("ingredients") ?: arrayListOf()

            val listView = findViewById<ListView>(R.id.listView)
            val backButton = findViewById<Button>(R.id.btnBack)
            val allIRecipesButton = findViewById<Button>(R.id.btnShowAll)
            val avgButton = findViewById<Button>(R.id.btnAverage)


            val filteredList = ArrayList<String>()
            for (i in recipes.indices) {
                if (ratings[i] >= 2) {
                    val entry = "${recipes[i]} (${categories[i]}) x${ratings[i]} - ${ingredients[i]}"
                    filteredList.add(entry)
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredList)
            listView.adapter = adapter

            allIRecipesButton.setOnClickListener {
                val allList = ArrayList<String>()
                for (i in recipes.indices) {
                    val entry = "${recipes[i]} (${categories[i]}) x${ratings[i]} - $ingredients[i]}"
                    allList.add(entry)
                }
                listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, allList)
            }

            avgButton.setOnClickListener {
                if (ratings.isNotEmpty()) {
                    val avg = ratings.sum().toDouble() / ratings.size
                    Toast.makeText(this, "Average Ratings: %.2f".format(avg), Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "No items to calculate average", Toast.LENGTH_SHORT).show()
                }
            }


            backButton.setOnClickListener {
                finish()





            }
    }
}}