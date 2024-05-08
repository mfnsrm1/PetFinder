import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;

public class PetFinderBackend {
    // Banco de dados para armazenar informações dos cachorros
    private static Map<String, Cachorro> cachorrosPerdidos = new HashMap<>();
    private static Map<String, Cachorro> cachorrosAchados = new HashMap<>();
    private static Map<String, Cachorro> cachorrosParaAdocao = new HashMap<>();

    public static void main(String[] args) {
        // Configura rotas para manipular diferentes tipos de solicitações

        // Rota para cachorros perdidos
        path("/cachorrosperdidos", () -> {
            post("/cadastro", (req, res) -> {
                // Manipula o cadastro de cachorros perdidos
                // Extrai dados da solicitação e armazena no banco de dados
                Cachorro cachorro = extractCachorroFromRequest(req);
                cachorrosPerdidos.put(cachorro.nome, cachorro);
                return "Cadastro de cachorro perdido realizado com sucesso!";
            });

            get("/pesquisa", (req, res) -> {
                // Manipula a pesquisa de cachorros perdidos
                // Extrai parâmetros de pesquisa da solicitação e retorna resultados
                String nome = req.queryParams("nome");
                Cachorro cachorro = cachorrosPerdidos.get(nome);
                return cachorro != null ? cachorro.toString() : "Cachorro não encontrado.";
            });

            // Rotas adicionais para operações de atualização e exclusão podem ser
            // adicionadas aqui
        });

        // Rota para cachorros achados
        path("/cachorrosachados", () -> {
            post("/cadastro", (req, res) -> {
                // Manipula o cadastro de cachorros achados
                // Extrai dados da solicitação e armazena no banco de dados
                Cachorro cachorro = extractCachorroFromRequest(req);
                cachorrosAchados.put(cachorro.nome, cachorro);
                return "Cadastro de cachorro achado realizado com sucesso!";
            });

            get("/pesquisa", (req, res) -> {
                // Manipula a pesquisa de cachorros achados
                // Extrai parâmetros de pesquisa da solicitação e retorna resultados
                String nome = req.queryParams("nome");
                Cachorro cachorro = cachorrosAchados.get(nome);
                return cachorro != null ? cachorro.toString() : "Cachorro não encontrado.";
            });

            // Rotas adicionais para operações de atualização e exclusão podem ser
            // adicionadas aqui
        });

        // Rota para cachorros para adoção
        path("/cachorrosadocao", () -> {
            post("/cadastro", (req, res) -> {
                // Manipula o cadastro de cachorros para adoção
                // Extrai dados da solicitação e armazena no banco de dados
                Cachorro cachorro = extractCachorroFromRequest(req);
                cachorrosParaAdocao.put(cachorro.nome, cachorro);
                return "Cadastro de cachorro para adoção realizado com sucesso!";
            });

            get("/pesquisa", (req, res) -> {
                // Manipula a pesquisa de cachorros para adoção
                // Extrai parâmetros de pesquisa da solicitação e retorna resultados
                String nome = req.queryParams("nome");
                Cachorro cachorro = cachorrosParaAdocao.get(nome);
                return cachorro != null ? cachorro.toString() : "Cachorro não encontrado.";
            });

            // Rotas adicionais para operações de atualização e exclusão podem ser
            // adicionadas aqui
        });
    }

    // Método para extrair dados de um cachorro da requisição
    private static Cachorro extractCachorroFromRequest(spark.Request req) {
        String nome = req.queryParams("nome");
        String porte = req.queryParams("porte");
        String especie = req.queryParams("especie");
        String raca = req.queryParams("raca");
        String cor = req.queryParams("cor");
        return new Cachorro(nome, porte, especie, raca, cor);
    }
}

// Classe para representar as características de um cachorro
class Cachorro {
    public String nome;
    private String porte;
    private String especie;
    private String raca;
    private String cor;

    // Construtor
    public Cachorro(String nome, String porte, String especie, String raca, String cor) {
        this.nome = nome;
        this.porte = porte;
        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
    }

    // Getters e setters
    // Implementar conforme necessário

    // Método para converter o cachorro em uma string
    @Override
    public String toString() {
        return "Cachorro{" +
                "nome='" + nome + '\'' +
                ", porte='" + porte + '\'' +
                ", especie='" + especie + '\'' +
                ", raça='" + raca + '\'' +
                ", cor='" + cor + '\'' +
                '}';
    }
}
