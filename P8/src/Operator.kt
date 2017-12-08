enum class Operator {
    INC{
        override fun invoke(a: Int, b: Int): Int {
            return a.plus(b)
        }
    },
    DEC{
        override fun invoke(a: Int, b: Int): Int {
            return a.minus(b)
        }
    };

    companion object {
        fun valueOfStr(value: String) :Operator {
            return Operator.valueOf(value.toUpperCase())
        }
    }

    abstract fun invoke(a: Int, b: Int): Int
}