package za.co.varsitcollege.st10450561.imad_practicum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val recipeList = ArrayList<String>()
    private val categoryList = ArrayList<String>()
    private val ratingList = ArrayList<Int>()
    private val ingredientList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

            val recipeEditText = findViewById<EditText>(R.id.editRecipe)
            val categoryEditText = findViewById<EditText>(R.id.editCategory)
            val ratingEditText = findViewById<EditText>(R.id.editRating)
            val ingredientEditText = findViewById<EditText>(R.id.editIngredient)

            val addButton = findViewById<Button>(R.id.btnAdd)
            val viewButton = findViewById<Button>(R.id.btnView)
            val exitButton = findViewById<Button>(R.id.btnExit)

            addButton.setOnClickListener {

            val recipe = recipeEditText.text.toString()
            val category = categoryEditText.text.toString()
            val ratingText = ratingEditText.text.toString()
            val ingredients = ingredientEditText.text.toString()

            if (recipe.isBlank() || category.isBlank() || ratingText.isBlank() || ingredients.isBlank()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            val rating = ratingText.toIntOrNull()
            if (rating == null || rating <= 0) {
                Toast.makeText(this, "Enter a valid rating", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            recipeList.add(recipe)
            categoryList.add(category)
            ratingList.add(rating)
            ingredientList.add(ingredients)

            Toast.makeText(this, "Recipe Added", Toast.LENGTH_SHORT).show()

            recipeEditText.text.clear()
            categoryEditText.text.clear()
            ratingEditText.text.clear()
            ingredientEditText.text.clear()




        }

            viewButton.setOnClickListener {
                val intent = Intent(this, ViewActivity::class.java)
                intent.putStringArrayListExtra("recipes", recipeList)
                intent.putStringArrayListExtra("categories", categoryList)
                intent.putIntegerArrayListExtra("ratings", ratingList)
                intent.putStringArrayListExtra("ingredients", ingredientList)
                startActivity(intent)
            }

            exitButton.setOnClickListener {
                finish()
            }



        }
    }
}