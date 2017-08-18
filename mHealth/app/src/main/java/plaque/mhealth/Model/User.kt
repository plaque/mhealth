package plaque.mhealth.Model

/**
 * Created by szymon on 18.08.17.
 */
data class User(val name: String, val surname: String, val email: String, var age: Int,
                var notes: ArrayList<Note>, var sitters: ArrayList<User>, var pupils: ArrayList<User>) {
}