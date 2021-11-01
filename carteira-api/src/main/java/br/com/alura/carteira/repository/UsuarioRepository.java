package br.com.alura.carteira.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


import br.com.alura.carteira.modelo.Usuario;

// para o spring poder injetar o entity manager

// mesmo processo do repository que foi utilizado na classe transacao

//@Repository

// o long se refere ao tipo d chave primaria da tabela Usuarios
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


//	// processo do private é para receber o parametro de conesão na classe e para isso criamos o construtor desta classe para receber como parametro	
//// na JPA vai ter o EntityManager no lugar da Connection
//	
////	private Connection conexao;
//	
//	@Autowired
//	private EntityManager em;
//
////		public TransacaoRepository(Connection conexao) {
////	    	this.conexao = conexao;
////	}
//
//		public void salvar(Usuario usuario) {
//              em.persist(usuario);
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
//		public List<Usuario> listar() {
//
//			// select de entidade e não de tabela... Usuario no caso é uma Entidade
//			
//			return em.createQuery("select u from Usuario u",Usuario.class)
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
	
	  // devolve ou não o objetos na consulta -- optional do java 8
	 Optional <Usuario> findByLogin(String login);
}
