enum class Compa(syn: String) {

    LESS("<") {
        override fun compare(a: Int, b: Int): Boolean {
            return a < b
        }
    },
    LARGER(">") {
        override fun compare(a: Int, b: Int): Boolean {
            return a > b
        }
    },
    EQ("==") {
        override fun compare(a: Int, b: Int): Boolean {
            return a == b
        }
    },
    NEQ("!=") {
        override fun compare(a: Int, b: Int): Boolean {
            return a != b
        }
    },
    GEQ(">=") {
        override fun compare(a: Int, b: Int): Boolean {
            return a >= b
        }
    },
    LEQ("<=") {
        override fun compare(a: Int, b: Int): Boolean {
            return a <= b
        }
    };

    val syn = syn

    companion object {
        fun valueOfStr(value: String) :Compa {
            for (comp in Compa.values()) {
                if (comp.syn.equals(value))
                    return comp
            }
            throw RuntimeException("No Compa with syn $value")
        }
    }
    abstract fun compare(a: Int, b: Int): Boolean
}