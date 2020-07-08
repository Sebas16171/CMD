public class Variable {
    private static String Nombre;
    private static float Valor;

    public String GetNombre(){
        return Nombre;
    }

    public static float GetValor(){
        return Valor;
    }

    public Variable(String iNombre, float iValor){
        Nombre = iNombre;
        Valor = iValor;
    }
}