import java.util.*;

public class Comando {
    private final String Valor;
    private float resultado = 0;
    ArrayList<String> Parametros = new ArrayList<String>();

    public Comando(final String iValor, final ArrayList<String> iParametros) {
        Valor = iValor;
        Parametros = iParametros;

        // Command_save_debug();

    }

    public void Command_save_debug() {
        System.out.println(
                "Se registro un comando con el valor: " + Valor + "\n" + "Y con " + Parametros.size() + " parametros");
        for (final String Parametro : Parametros) {
            System.out.println(Parametro);
        }

    }

    public String GetValor() {
        return Valor;
    }

    public ArrayList<String> GetParametros() {
        return Parametros;
    }

    public int ProcesarComando() {

        // Return -1 Saliendo
        // Return 0 Ok
        // Return 1 Error Comando no encontrado
        // Return 2 Error La cantidad de parametros no coincide
        // Return 3 Error El parametro no coincide con el tipo requerido
        // Return 4 Error La variable ya existe
        // Return 5 Error Nombre de la variable inicia con numero
        // Return 6 Aviso Ninguna variable registrada
        // Return 7 Error Variable no encontrada

        switch (Valor) {
            case "alv":
                return -1;

            case "NwP":
                String Nuevo_Prompt = "";
                for (final String Palabra : Parametros) {
                    Nuevo_Prompt += Palabra + " ";
                }
                Main.Cambiar_Prompt(Nuevo_Prompt);
                break;

            case "Var":
                if (Parametros.size() != 2 && Parametros.size() != 1) {
                    Main.Salida_Extra[0] = "2";
                    Main.Salida_Extra[1] = Parametros.size() + "";
                    return 2;
                } else {
                    Variable tempVar = null;
                    final String iNombre = Parametros.get(0);
                    float iValor = 0;

                    if (Parametros.size() == 2) {
                        try {
                            iValor = Float.valueOf(Parametros.get(1));
                        } catch (final Exception e) {
                            Main.Salida_Extra[0] = "numerico";
                            return 3;
                        }
                    }

                    for (final Variable varEnlistada : Main.Variables) {
                        if (varEnlistada.GetNombre().equals(iNombre)) {
                            return 4;
                        }
                    }

                    if (Character.isDigit(iNombre.charAt(0))) {
                        return 5;
                    }

                    tempVar = new Variable(iNombre, iValor);
                    Main.Variables.add(tempVar);
                }
                break;

            case "lstVar":
                if ((Main.Variables.size() != 0) && (Parametros.size() == 0)) {
                    String Salida = "";

                    for (final Variable CheckingVar : Main.Variables) {
                        Salida += CheckingVar.GetNombre() + "\t|\t" + CheckingVar.GetValor() + "\n";
                    }
                    Main.Salida_Extra[0] = Salida;
                } else if ((Main.Variables.size() != 0) && (Parametros.size() == 1)){
                    for (final Variable checkingVar : Main.Variables) {
                        if (checkingVar.GetNombre().equals(Parametros.get(0))) {
                            System.out.println(checkingVar.GetNombre() + "\t|\t" + checkingVar.GetValor());
                            break;
                        }
                    }
                } else if (Main.Variables.size() != 0 && Parametros.size() > 1) {
                    Main.Salida_Extra[0] = "1";
                    Main.Salida_Extra[1] = Parametros.size() + "";
                    return 2;
                } else {
                    return 6;
                }
                break;

            case "cng":
                if (Parametros.size() != 2 && Parametros.size() != 1) {
                    Main.Salida_Extra[0] = "2";
                    Main.Salida_Extra[1] = Parametros.size() + "";
                    return 2;
                } else if (Main.Variables.size() == 0) {
                    return 6;
                } else {
                    Variable WorkingVar = null;
                    Variable WorkingVar2 = null;
                    float nwValor = 0;

                    for (final Variable checkingVar : Main.Variables) {
                        if (checkingVar.GetNombre().equals(Parametros.get(0))) {
                            WorkingVar = checkingVar;
                            break;
                        }
                    }

                    if (WorkingVar == null) {
                        Main.Salida_Extra[0] = Parametros.get(0);
                        return 7;
                    }

                    if (!Character.isDigit(Parametros.get(1).charAt(0))) {
                        for (final Variable checkingVar2 : Main.Variables) {
                            if (Parametros.get(1).equals(checkingVar2.GetNombre())) {
                                WorkingVar2 = checkingVar2;
                                break;
                            }
                        }
                        if (WorkingVar2 != null) {
                            nwValor = WorkingVar2.GetValor();
                        } else{
                            Main.Salida_Extra[0] = "numerico o variable";
                            return 3;

                        }                 
                    } else {
                        nwValor = Float.valueOf(Parametros.get(1));
                    } 
                    WorkingVar.SetValor(nwValor);
                }
            break;

            /*case "operacion":

                if (Parametros.size() == 3) {

                    ArrayList<Float> valores = new ArrayList<Float>();
                    float resultado = 0;

                    Variable workingVariable = null;

                    if (!Character.isDigit(Parametros.get(0).charAt(0))) {
                        for (Variable checkingVar : Main.Variables) {
                            if (checkingVar.GetNombre().equals(Parametros.get(0))) {
                                workingVariable = checkingVar;
                                break;
                            }

                        }
                    }

                    for (int i = 0; i < Parametros.size(); i++) {
                        if (i == 1 || i % 3 == 0) {
                            if (Character.isDigit(Parametros.get(i).charAt(0))) {
                                valores.add(Float.valueOf(Parametros.get(i)));
                            } else {
                                for (Variable checkingVar : Main.Variables) {
                                    if (checkingVar.GetNombre().equals(Parametros.get(i))) {
                                        valores.add(checkingVar.GetValor());
                                        break;
                                    }
                                    
                                }
                            }
                        }
                        
                    }

                    for (Float float1 : valores) {
                        System.out.println(float1);
                    }
                    
                    switch (Parametros.get(1)) {
                        case "+":
                            resultado = valores.get(0) + valores.get(1);
                            System.out.println(resultado);
                        break;
                        case "-":
                            resultado = valores.get(0) - valores.get(1);
                            System.out.println(resultado);
                        break;
                        case "*":
                            resultado = valores.get(0) * valores.get(1);
                            System.out.println(resultado);
                        break;
                        case "/":
                            resultado = valores.get(0) / valores.get(1);
                            System.out.println(resultado);
                        break;
                        case "%":
                            resultado = valores.get(0) % valores.get(1);
                            System.out.println(resultado);
                        break;
                    
                        default:
                            break;
                    }
                } else {
                    Main.Salida_Extra[0] = "3";
                    Main.Salida_Extra[1] = Parametros.size() + "";
                    return 2;
                }

            break;*/
            
            case "suma":
                try {
                    resultado = Float.valueOf(Parametros.get(0));
                } catch (Exception e) {
                    boolean encontrado = false;
                    for (Variable CheckingVar : Main.Variables) {
                        if (Parametros.get(0).equals(CheckingVar.GetNombre())) {
                            resultado += CheckingVar.GetValor();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        Main.Salida_Extra[0] = "numerico o variable";
                        return 3;
                    }
                }

                for (String parametro : Parametros.subList(1, Parametros.size())) {
                    if (Character.isDigit(parametro.charAt(0))){
                        resultado += Float.valueOf(parametro) ;
                    } else {
                        boolean encontrado = false;
                        for (Variable CheckingVar : Main.Variables) {
                            if (parametro.equals(CheckingVar.GetNombre())) {
                                resultado += CheckingVar.GetValor();
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            Main.Salida_Extra[0] = "numerico o variable";
                            return 3;
                        }
                    }
                }

                System.out.println(resultado);
            break;

            case "resta":
                    try {
                    resultado = Float.valueOf(Parametros.get(0));
                } catch (Exception e) {
                    boolean encontrado = false;
                    for (Variable CheckingVar : Main.Variables) {
                        if (Parametros.get(0).equals(CheckingVar.GetNombre())) {
                            resultado += CheckingVar.GetValor();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        Main.Salida_Extra[0] = "numerico o variable";
                        return 3;
                    }
                }

                for (String parametro : Parametros.subList(1, Parametros.size())) {
                    if (Character.isDigit(parametro.charAt(0))) {
                        resultado -= Float.valueOf(parametro);
                    } else {
                        boolean encontrado = false;
                        for (Variable CheckingVar : Main.Variables) {
                            if (parametro.equals(CheckingVar.GetNombre())) {
                                resultado -= CheckingVar.GetValor();
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            Main.Salida_Extra[0] = "numerico o variable";
                            return 3;
                        }
                    }
                }

                System.out.println(resultado);
            break;

            case "multiplicacion":
                    try {
                    resultado = Float.valueOf(Parametros.get(0));
                } catch (Exception e) {
                    boolean encontrado = false;
                    for (Variable CheckingVar : Main.Variables) {
                        if (Parametros.get(0).equals(CheckingVar.GetNombre())) {
                            resultado += CheckingVar.GetValor();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        Main.Salida_Extra[0] = "numerico o variable";
                        return 3;
                    }
                }

                for (String parametro : Parametros.subList(1, Parametros.size())) {
                    if (Character.isDigit(parametro.charAt(0))) {
                        resultado = resultado * Float.valueOf(parametro);
                    } else {
                        boolean encontrado = false;
                        for (Variable CheckingVar : Main.Variables) {
                            if (parametro.equals(CheckingVar.GetNombre())) {
                                resultado = resultado * CheckingVar.GetValor();
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            Main.Salida_Extra[0] = "numerico o variable";
                            return 3;
                        }
                    }
                }

                System.out.println(resultado);
            break;

            case "division":
                boolean error = false;
                try {
                    resultado = Float.valueOf(Parametros.get(0));
                } catch (Exception e) {
                    boolean encontrado = false;
                    for (Variable CheckingVar : Main.Variables) {
                        if (Parametros.get(0).equals(CheckingVar.GetNombre())) {
                            resultado += CheckingVar.GetValor();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        Main.Salida_Extra[0] = "numerico o variable";
                        return 3;
                    }
                }

                for (String parametro : Parametros.subList(1, Parametros.size())) {
                    if (Character.isDigit(parametro.charAt(0))) {
                        if (Float.valueOf(parametro) == 0) {
                            error = true;
                        }
                        resultado = resultado / Float.valueOf(parametro);
                    } else {
                        boolean encontrado = false;
                        for (Variable CheckingVar : Main.Variables) {
                            if (parametro.equals(CheckingVar.GetNombre())) {
                                if (Float.valueOf(CheckingVar.GetValor()) == 0) {
                                    error = true;
                                }
                                resultado = resultado / CheckingVar.GetValor();
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            Main.Salida_Extra[0] = "numerico o variable";
                            return 3;
                        }
                    }
                }
                if (error) {
                    System.out.println("No se puede dividir entre 0");
                } else {
                    System.out.println(resultado);

                }
            break;

            case "modulo":
            boolean error2 = false;
                    try {
                    resultado = Float.valueOf(Parametros.get(0));
                } catch (Exception e) {
                    boolean encontrado = false;
                    for (Variable CheckingVar : Main.Variables) {
                        if (Parametros.get(0).equals(CheckingVar.GetNombre())) {
                            resultado += CheckingVar.GetValor();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        Main.Salida_Extra[0] = "numerico o variable";
                        return 3;
                    }
                }

                for (String parametro : Parametros.subList(1, Parametros.size())) {
                    if (Character.isDigit(parametro.charAt(0))) {
                        if (Float.valueOf(parametro) == 0) {
                            error2 = true;
                        }
                        resultado = resultado % Float.valueOf(parametro);
                    } else {
                        boolean encontrado = false;
                        for (Variable CheckingVar : Main.Variables) {
                            if (parametro.equals(CheckingVar.GetNombre())) {
                                if (CheckingVar.GetValor() == 0) {
                                    error2 = true;
                                }
                                resultado = resultado % CheckingVar.GetValor();
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            Main.Salida_Extra[0] = "numerico o variable";
                            return 3;
                        }
                    }
                }
                if (error2) {
                    System.out.println("No se puede dividir entre 0");
                } else {
                    System.out.println(resultado);

                }
            break;

            case "aiuda":

                if (Parametros.size() > 1) {
                    Main.Salida_Extra[0] = "1";
                    Main.Salida_Extra[1] = Parametros.size() + "";
                    return 2;
                }
                
                System.out.println("\nalv\t\t\t\tSale del programa");
                System.out.println("NwP [prompt]\t\t\tCambia el prompt");
                System.out.println("Var [Nombre] [Valor]\t\tCrea una variable");
                System.out.println("lstVar [Nombre]\t\t\tMuestra una o todas las variables");
                System.out.println("cng [Nombre] [Valor]\t\tCambia el valor de una variable");
                System.out.println("suma [{Valores}]\t\tSuma dos o mas numeros");
                System.out.println("resta [{Valores}]\t\tResta dos o mas numeros");
                System.out.println("multiplicacion [{Valores}]\tMultiplica dos o mas numeros");
                System.out.println("division [{Valores}]\t\tDivide dos o mas numeros");
                System.out.println("modulo [{Valores}]\t\tResiduo de la division entre dos o mas numeros");
                System.out.println("aiuda\t\t\t\tMuestra este mensaje de ayuda\n");
            break;

            default:
                if (Valor.endsWith("++")) {
                    String nombre_variable = Valor.substring(0, Valor.length() - 2);
                    for (Variable WorkingVar : Main.Variables) {
                        if (nombre_variable.equals(WorkingVar.GetNombre())) {
                            WorkingVar.SetValor(WorkingVar.GetValor() + 1);
                        }
                    }
                    return 0;
                } else if(Valor.endsWith("--")){
                    String nombre_variable = Valor.substring(0, Valor.length() - 2);
                    for (Variable WorkingVar : Main.Variables) {
                        if (nombre_variable.equals(WorkingVar.GetNombre())) {
                            WorkingVar.SetValor(WorkingVar.GetValor() - 1);
                        }
                    }
                    return 0;
                } else {
                    return 1;
                }
        }
        return 0;
    }

}


 