package GerenciadorDeAcademiWeb.Enums;


public enum SexoEnum{
    MASCULINO(1),
    FEMININO(2);

    public int valorSexo;

    SexoEnum(int valor) {
        valorSexo = valor;
    }

    public int getInt(){
        return valorSexo;
    }
}