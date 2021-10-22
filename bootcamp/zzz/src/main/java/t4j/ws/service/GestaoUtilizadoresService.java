package t4j.ws.service;

import t4j.ws.domain.Contexto;
import t4j.ws.domain.Sessao;
import t4j.ws.domain.Utilizador;
import t4j.ws.dto.ContextoDTO;
import t4j.ws.dto.LoginDTO;
import t4j.ws.dto.Mapper;
import t4j.ws.dto.SessaoDTO;
import t4j.ws.exception.KeyInvalidaException;
import t4j.ws.persistence.RepositorioRolename;
import t4j.ws.persistence.RepositorioSessao;
import t4j.ws.persistence.RepositorioUtilizador;

import java.sql.SQLException;

public class GestaoUtilizadoresService {

    private static RepositorioSessao repositorioSessao = RepositorioSessao.getInstance();
    private static RepositorioUtilizador repositorioUtilizador = RepositorioUtilizador.getInstance();
    private static RepositorioRolename repositorioRolename = RepositorioRolename.getInstance();

    public static ContextoDTO generateContext(String appKey) throws SQLException {
        Contexto contexto = null;

        try {
            contexto = new Contexto(appKey);
        }
        catch (KeyInvalidaException exception) {
            exception.printStackTrace();
            exception.getMessage();
        }

        ContextoDTO contextoDTO = Mapper.contexto2ContextoDTO(contexto);

        repositorioSessao.saveContext(contexto);
        return contextoDTO;
    }

    public static boolean validateContexto(ContextoDTO contextoDTO) throws SQLException {
        Contexto contexto = repositorioSessao.findContextByString(contextoDTO.getAppContext());

        return contexto.isValido();
    }

    public static boolean validateSetUsableContexto(ContextoDTO contextoDTO) throws SQLException {
        Contexto contexto = repositorioSessao.findContextByString(contextoDTO.getAppContext());

        if (contexto == null) {
            return false;
        }
        repositorioSessao.setUsableOrDiscarded(contexto);
        return contexto.isValido();

    }

    public static boolean login(LoginDTO loginDTO, ContextoDTO contextoDTO) throws SQLException {
        Utilizador utilizador = repositorioUtilizador.findByEmail(loginDTO.getEmail().toString());

        if(!(utilizador != null && (utilizador.getPassword().toString().equals(loginDTO.getPassword().toString())))) {
            return false;
        }

        Contexto contexto = repositorioSessao.findContextByString(contextoDTO.getAppContext());

        int idRolename = repositorioRolename.getIdByEmail(utilizador.getEmail().toString());

        Sessao sessao = new Sessao(idRolename, contexto.getIdContexto(), utilizador.getEmail().toString());

        return repositorioSessao.saveSessao(sessao);
    }

    public static boolean logout(ContextoDTO contextoDTO) throws SQLException {
        Contexto contexto = repositorioSessao.findContextByString(contextoDTO.getAppContext());

        return repositorioSessao.contextInvalid(contexto) ;
    }

    public static SessaoDTO getSession(ContextoDTO contextoDTO) throws SQLException {
        Sessao sessao = repositorioSessao.findByContext(contextoDTO.getAppContext());

        if(sessao == null) {
            return null;
        }

        SessaoDTO sessaoDTO = Mapper.sessao2SessaoDTO(sessao);
        return sessaoDTO;
    }
}
