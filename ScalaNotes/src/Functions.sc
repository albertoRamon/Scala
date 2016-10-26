
// Basic Function
 def sum1 (a:Int,b:Int):Int = a+b
 sum1(1,2)

def pinta (s:String):Unit =println("no devolvemos nada")


// High order functions
def cuadrado1 (x:Int):Int = x*x    	//devuelve una función
val myCuadrado1:Int => Int =cuadrado1 //un valor que apunta a una funcion

def cuadrado (Int):Int = _*_    	//versión abreviada