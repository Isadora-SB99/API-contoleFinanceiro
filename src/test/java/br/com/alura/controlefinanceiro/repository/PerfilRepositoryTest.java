package br.com.alura.controlefinanceiro.repository;

import br.com.alura.controlefinanceiro.model.entities.Perfil;
import br.com.alura.controlefinanceiro.model.repository.PerfilRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PerfilRepositoryTest {

    @Autowired
    private PerfilRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaCarregarPerfilPorNome(){
        String nomePerfil = "USER";

        Perfil user = new Perfil();
        user.setNome(nomePerfil);
        em.persist(user);

        Perfil perfil = repository.findByNome(nomePerfil);
        Assertions.assertNotNull(perfil);
        Assertions.assertEquals(nomePerfil, perfil.getNome());
    }

    @Test
    public void naoDeveriaCarregarPerfilNaoCadastrado(){
        String nomePerfil = "MODERADOR";
        Perfil perfil = repository.findByNome(nomePerfil);
        Assertions.assertNull(perfil);
    }
}
