public class Variable {
    private String Nombre;
    private float Valor;

    public String GetNombre(){
        return Nombre;
    }

    public float GetValor(){
        return Valor;
    }

    public void SetValor(float iValor){
        Valor = iValor;
    }

    public Variable(String iNombre, float iValor){
        Nombre = iNombre;
        Valor = iValor;
    }
}