enum class Direction {
    RIGHT {
        override fun next() = UP
        override fun prev() = DOWN
    },
    UP{
        override fun next() = LEFT
        override fun prev() = RIGHT
    },
    LEFT {
        override fun next() = DOWN
        override fun prev() = UP
    },
    DOWN {
        override fun next() = RIGHT
        override fun prev() = LEFT
    };

    abstract fun next(): Direction
    abstract fun prev(): Direction
}
