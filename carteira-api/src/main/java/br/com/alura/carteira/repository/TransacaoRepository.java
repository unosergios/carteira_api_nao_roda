package br.com.alura.carteira.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.modelo.Transacao;




// para o spring poder injetar o entity manager colocar a anotação @Repositor
// podemos melhorar ainda mais este codigo e deixa-lo mais simples
// vai ser tirado o @repository e a public class TransacaoRepository será agora uma inteface
//@Repository
//public class TransacaoRepository {

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	// processo do private é para receber o parametro de conesão na classe e para isso criamos o construtor desta classe para receber como parametro	
// na JPA vai ter o EntityManager no lugar da Connection
	
//	private Connection conexao;
	
//	@Autowired
//	private EntityManager em;
//
////		public TransacaoRepository(Connection conexao) {
////	    	this.conexao = conexao;
////	}
//
//		public void salvar(Transacao transacao) {
//              em.persist(transacao);
////	    // como sempre repete da para mehor este codigo e recebemo como o parametro a conexao-- entao as linhas abaixo serão comentadas
////		//	String url = "jdbc:mysql://localhost:3306/carteira?useSSL=false&serverTimezone=UTC";
////		//	String usuario = "root";
////	    //	String senha = "root";
////			try {
////
////			//	Connection conexao = DriverManager.getConnection(url, usuario, senha); // metodo static não usa New -- vai receber a coneão por parfametro
////
////				String sql = "insert into transacoes (ticker,preco,quantidade,data,tipo) values(?,?,?,?,?)";
////
////				// prepara a declaração atrelando a conexao
////
////				PreparedStatement ps = conexao.prepareStatement(sql);
////
////				ps.setString(1, regtransgravar.getTicker());
////				ps.setBigDecimal(2, regtransgravar.getPreco());
////				ps.setInt(3, regtransgravar.getQuantidade());
////
////				// nao tem o LocalDate porque é uma coisa que veio recentemente e o JDBC é muito
////				// antigo. Dai utilizar o date e transformar o LocalDate em date
////				ps.setDate(4, Date.valueOf(regtransgravar.getData())); // se olhar para a conversão o set pede o Date do sql
////																		// e não do java !! Atençao
////				ps.setString(5, regtransgravar.getTipo().toString());
////
////				// lanca para o sql
////
////				ps.execute();
////
////				
////
////			} catch (SQLException e) {
////				throw new RuntimeException(e);
////			}
//
//		}
//
//		public List<Transacao> listar() {
//
//			return em.createQuery("select u from Transacao t",Transacao.class)
//					.getResultList();
////			// como sempre repete da para mehor este codigo
//////			String url = "jdbc:mysql://localhost:3306/carteira?useSSL=false&serverTimezone=UTC";
//////			String usuario = "root";
//////			String senha = "root";
////			try {
////
//////				Connection conexao = DriverManager.getConnection(url, usuario, senha); // metodo static não usa New
////
////				String sql = "select * from transacoes";
////
////				PreparedStatement ps = conexao.prepareStatement(sql);
////
////				ResultSet rs = ps.executeQuery();
////
////				List<Transacao> regsaida = new ArrayList<>();
////
////				while (rs.next()) {
////
////					Transacao t = new Transacao();
////
////					t.setTicker(rs.getString("ticker"));
////					t.setData(rs.getDate("data").toLocalDate());
////					t.setPreco(rs.getBigDecimal("preco"));
////					t.setQuantidade(rs.getInt("quantidade"));
////					t.setTipo(TipoTransacao.valueOf(rs.getString("tipo")));
////
////					regsaida.add(t);
////					// System.out.println(t);
////
////				}
////
////				return regsaida;
////
////			} catch (SQLException e) {
////
////				throw new RuntimeException(e);
////			}
////
//		}              
	
	// como testar uma interface
	//
	// query voltando todos os campos vai ser substituido por quer que volta 
	// a somatoria de quantidades e o calculo do percentual fica dentro da DTO
	// devido a questão das casas decimais
	
//	@Query("select new br.com.alura.carteira.dto.ItemCarteiraDto( "
//			+ "t.ticker,"
//			+ "sum(case when(t.tipo='COMPRA') then  t.quantidade else (t.quantidade *-1.0)end),"
//			+ "sum(case when(t.tipo='COMPRA') then  t.quantidade *1.0 else (t.quantidade *-1.0)end) * 1.0 /(select sum(t2.quantidade) from Transacao t2)* 1.0 )"
//			+ " from Transacao t"
//			+ " group by t.ticker")
	
	@Query("select new br.com.alura.carteira.dto.ItemCarteiraDto( "
	+ "t.ticker,"
	+ "sum(case when(t.tipo='COMPRA') then  t.quantidade else (t.quantidade *-1.0)end),"
	+ "(select sum(case when(t2.tipo='COMPRA') then  t2.quantidade else (t2.quantidade *-1.0)end) from Transacao t2))"
	+ " from Transacao t"
	+ " group by t.ticker")	
	List<ItemCarteiraDto> relatorioCarteiraDeInvestimentos();
	
	
}
