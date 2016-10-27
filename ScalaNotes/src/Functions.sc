
// Basic Function
 def sum1 (a:Int,b:Int):Int = a+b
 sum1(1,2)

def pinta (s:String):Unit =println("no devolvemos nada")


// High order functions
def cuadrado1 (x:Int):Int = x*x    	//devuelve una función
val myCuadrado1:Int => Int =cuadrado1 //un valor que apunta a una funcion
  // --> Funcion como objeto
  // --> La salida de la funcion es una funcion
val myCuadrado2 =cuadrado1 _        //un valor que apunta a una funcion
val myCuadrado3:(Int)=>Int =cuadrado1 //un valor que apunta a una funcion
// los parentesis se peuden omitir cuando solo hay un param de entrada, sino ponemos (x,y)

cuadrado1 (5)      //Llamando a la funcion
myCuadrado1(5)     //Llamando a la variable
myCuadrado2(5)     //Llamando a la variable
myCuadrado3(5)     //Llamando a la variable

//Ejemplo con mas de un parametro
def multiplica1 (x:Int,y:Int):Int = x*y
val myMultiplica1:(Int,Int)=>Int= multiplica1

multiplica1 (2,2)
myMultiplica1 (2,2)


//Ejemplo sin parametros, hay uq poner () que casuelmente es la representacion de Unit Type
def pinta1 () ="Hi"
def pinta2 () ="Hi"
val myPinta:()=> String=pinta1
pinta1
myPinta


//def cuadrado (Int):Int = _*_    	//versión abreviada