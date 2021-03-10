class Vincent {

    var name = "vincent"

    fun start() {
        try {
            println("Write your name:")
            name = readLine()!!

            println("How old are you?")
            val age = readLine()!!


            println("Hello my name is $name and I am $age years old.")


//            for(i in 1..100) {
//                println(" O   O ")
//                println("   |   ")
//                println(" -   - ")
//                println("  ---  ")
//            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//    fun start() {
////        val s = readLine()
////        println("Test: $s")
//
//        val i = System.`in`.read()
//
////        val console = System.console()
////        val reader = console.reader()
////        val i = reader.read()
//        println("Test: $i")
//    }

}