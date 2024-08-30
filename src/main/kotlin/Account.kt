open class Account(val name: String, private val pw: String, var age: Int) {
    override fun toString(): String {
        return """
            Name: $name
            Alter: $age
        """
    }
    fun checkPassword(inputPw: String): Boolean {
        return pw == inputPw
    }

}