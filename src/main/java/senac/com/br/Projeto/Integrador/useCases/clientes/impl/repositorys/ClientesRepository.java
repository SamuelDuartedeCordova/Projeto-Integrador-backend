package senac.com.br.Projeto.Integrador.useCases.clientes.impl.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.com.br.Projeto.Integrador.entitys.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
