package senac.com.br.Projeto.Integrador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senac.com.br.Projeto.Integrador.frameWork.annotions.LogRest;
import senac.com.br.Projeto.Integrador.frameWork.utils.ProFutException;
import senac.com.br.Projeto.Integrador.frameWork.utils.ResponseUtil;
import senac.com.br.Projeto.Integrador.useCases.clientes.domanis.ClientesRequestDom;
import senac.com.br.Projeto.Integrador.useCases.clientes.domanis.ClientesResponseDom;
import senac.com.br.Projeto.Integrador.useCases.clientes.impl.ClientesServiceImpl;
import senac.com.br.Projeto.Integrador.useCases.clientes.impl.repositorys.ClientesRepository;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    ClientesServiceImpl clientesService;

    @GetMapping("/carregar")
    @LogRest
    public ResponseEntity<List<ClientesResponseDom>> carregarClientes(){
        return ResponseEntity.ok(clientesService.carregarClientes());
    }

    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<ClientesResponseDom> carregarClienteById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(clientesService.carregarClienteById(id));
        } catch (ProFutException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarCliente(@RequestBody ClientesRequestDom clientesRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.criarCliente(clientesRequestDom));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody ClientesRequestDom clientesRequestDom) throws ProFutException {

        try {
            return ResponseEntity.ok(clientesService.atualizarClientes(id, clientesRequestDom));
        } catch (ProFutException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMapper("Erro não mapeado:" + e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    @LogRest
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clientesService.deletarCliente(id);

        return ResponseEntity.ok(null);
    }
}
