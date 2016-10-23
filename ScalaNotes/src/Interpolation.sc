
//Interpolation examples
// Hay tres tipos de interpolacion

// Interpolacion S
val name = "pepe"
println(s"nombre: $name")

val x:Int =1
val y:Int =2


//ver que evalua expresiones
println(s"La suma x+y= ${x+y}")
println(s"La suma x=1 ${x==1}")

//Interpolacion F
println(f"x= $x%.2f")
println(f"x= $x%.0f")

//Interpolacion RAW
println(s"a\nb")
println(raw"a\nb")