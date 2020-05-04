public class teste {
    public static void main(String[] args) {
        String teste = "<br> Inclusive o condutor dirigia manejando entre densa fumaça. \n" +
                "<br> Se até o motorista dirigia entre espirais de fumaça. \n" +
                "<img src=\"setanim.gif\" width=\"36\" height=\"23\" align=\"middle\"><br> Apenas o condutor dirigia aspirando o fumo. \n" +
                "<br> Mesmo o motorista manejava entre uma nuvem de fumaça. \n" +
                "<br>";

        teste = teste.replaceAll("\n", "")
                     .replaceAll("<img(\\s).*", "")
                     .replaceAll(".*<br>", "");
        System.out.println(teste);
    }
}
