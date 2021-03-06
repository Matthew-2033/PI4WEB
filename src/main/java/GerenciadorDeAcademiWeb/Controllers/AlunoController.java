package GerenciadorDeAcademiWeb.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ControleDeAcesso.LoginApi.LoginApi;
import GerenciadorDeAcademiWeb.Models.Aluno;
import GerenciadorDeAcademiWeb.Models.ResponseModels.ApiRetorno;
import Helper.GsonHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.squareup.okhttp.RequestBody;
import java.time.OffsetDateTime;
import okio.ByteString;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlunoController {

    @Value("${api.baseUrl}")
    private String baseUrl;

    @RequestMapping(value = {"/alunos"})
    public ModelAndView listAlunos() {
        ModelAndView modelAndView = new ModelAndView("Aluno/ListAluno");
        LoginApi loginApi = new LoginApi();

        try {
            String token = loginApi.logar();

            if (token == null) {
                return new ModelAndView("redirect:/logout");
            }

            ApiRetorno<List<Aluno>> alunosApi = getAlunos(token);

            modelAndView.addObject("alunos", alunosApi.getData());

            return modelAndView;
        } catch (Exception ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("Aluno/ListAluno");
        }
    }

    @RequestMapping(value = {"/cadastro"})
    public ModelAndView cadastro() {
        ModelAndView modelAndView = new ModelAndView("Aluno/FormALuno");
        LoginApi loginApi = new LoginApi();

        try {
            String token = loginApi.logar();

            if (token == null) {
                return new ModelAndView("redirect:/logout");
            }

            return modelAndView;

        } catch (Exception ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("Aluno/ListAluno");
        }

    }

    private ApiRetorno<List<Aluno>> getAlunos(String token) {
        Gson gson = GsonHelper.getGson();
        String urlAluno = "Api/Aluno";

        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(baseUrl + urlAluno)
                    .get()
                    .addHeader("Authorization", "Bearer" + token)
                    .build();

            Response responseApi = client.newCall(request).execute();
            String retornoJson = responseApi.body().string();

            ApiRetorno<List<Aluno>> response = gson.fromJson(retornoJson, new TypeToken<ApiRetorno<List<Aluno>>>() {
            }.getType());

            return response;

        } catch (Exception ex) {
            ApiRetorno<List<Aluno>> retorno = new ApiRetorno<List<Aluno>>();

            List<String> erros = new ArrayList<String>();
            erros.add(ex.getMessage());
            retorno.setErrorMessages(erros);
            retorno.setSucess(false);
            return retorno;
        }
    }

    @RequestMapping(value = {"/salva_aluno"}, method = RequestMethod.POST)
    public ModelAndView salvaAluno(@ModelAttribute("aluno") Aluno aluno, @RequestParam("sexo") Integer sexo, RedirectAttributes redirectAttributes) {
        Gson gson = GsonHelper.getGson();
        ModelAndView modelAndView = new ModelAndView("Aluno/FormAluno");
        LoginApi loginApi = new LoginApi();
        String urlAluno = "Api/Aluno";

        try {
            String token = loginApi.logar();

            MediaType media = MediaType.parse("application/json");

            aluno.setSexoEnum(sexo);
            aluno.setAtivo(true);

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(media, gson.toJson(aluno));

            Request request = new Request.Builder()
                    .url(baseUrl + urlAluno)
                    .post(body)
                    .addHeader("Authorization", "Bearer" + token)
                    .build();

            Response responseApi = client.newCall(request).execute();

            if (!responseApi.isSuccessful()) {
                redirectAttributes.addFlashAttribute("error", true);
                return new ModelAndView("redirect:/cadastro");
            }

            redirectAttributes.addFlashAttribute("success", true);
            return new ModelAndView("redirect:/alunos");

        } catch (Exception ex) {

            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("Aluno/FormAluno").addObject("error", true);
        }

    }

    @RequestMapping(value = {"edita/{id}"}, method = RequestMethod.GET)
    public ModelAndView editaAluno(@PathVariable String id) {
        Gson gson = GsonHelper.getGson();
        ModelAndView modelAndView = new ModelAndView("Aluno/FormEditAluno");
        LoginApi loginApi = new LoginApi();
        String urlAluno = "Api/Aluno/";

        try {

            String token = loginApi.logar();
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(baseUrl + urlAluno + "?id=" + id)
                    .get()
                    .addHeader("Authorization", "Bearer" + token)
                    .build();

            Response responseApi = client.newCall(request).execute();
            String retornoJson = responseApi.body().string();

            ApiRetorno<List<Aluno>> response = gson.fromJson(retornoJson,
                    new TypeToken<ApiRetorno<List<Aluno>>>() {
                    }.getType()
            );

            return modelAndView.addObject("aluno", response.getData().get(0));

        } catch (Exception ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("redirect:/alunos").addObject("error", ex);
        }

    }

    @RequestMapping(value = {"/salva_edicao"}, method = RequestMethod.GET)
    public ModelAndView salvaEdicao(@ModelAttribute("aluno") Aluno aluno, @RequestParam("sexo") Integer sexo, RedirectAttributes redirectAttributes) {
        Gson gson = GsonHelper.getGson();
        ModelAndView modelAndView = new ModelAndView("Aluno/FormAluno");
        LoginApi loginApi = new LoginApi();
        String urlAluno = "Api/Aluno";

        try {
            String token = loginApi.logar();

            MediaType media = MediaType.parse("application/json");

            aluno.setSexoEnum(sexo);
            aluno.setAtivo(true);

            OkHttpClient client = new OkHttpClient();
            String a = gson.toJson(aluno).toString();
            RequestBody body = RequestBody.create(media, gson.toJson(aluno));

            Request request = new Request.Builder()
                    .url(baseUrl + urlAluno)
                    .put(body)
                    .addHeader("Authorization", "Bearer" + token)
                    .build();

            Response responseApi = client.newCall(request).execute();

            if (!responseApi.isSuccessful()) {
                redirectAttributes.addFlashAttribute("error", true);
                return new ModelAndView("redirect:/edita/" + aluno.getId());
            }

            redirectAttributes.addFlashAttribute("success", true);
            return new ModelAndView("redirect:/alunos");

        } catch (Exception ex) {

            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("redirect:/edita/" + aluno.getId()).addObject("error", true);
        }
    }

    @RequestMapping(value = {"ativarAluno/{id}"}, method = RequestMethod.GET)
    public ModelAndView ativarAluno(@PathVariable String id) {
        Gson gson = GsonHelper.getGson();
        ModelAndView modelAndView = new ModelAndView("Aluno/ListAluno");
        LoginApi loginApi = new LoginApi();
        String urlAluno = "Api/Aluno";

        try {
            String token = loginApi.logar();

            if (token == null) {
                return new ModelAndView("redirect:/logout");
            }

            MediaType media = MediaType.parse("application/json");

            RequestBody body = RequestBody.create(media, gson.toJson(""));
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(baseUrl + urlAluno)
                    .patch(body)
                    .addHeader("Authorization", "Bearer" + token)
                    .addHeader("id", id)
                    .build();

            Response responseApi = client.newCall(request).execute();

            return modelAndView.addObject("reponse", gson.toJson(responseApi.body().toString()));

        } catch (Exception ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("redirect:/alunos");
        }
    }

    @RequestMapping(value = {"delete/{id}"}, method = RequestMethod.GET)
    public ModelAndView deleteAluno(@PathVariable String id) {
        Gson gson = GsonHelper.getGson();
        ModelAndView modelAndView = new ModelAndView("Aluno/ListAluno");
        LoginApi loginApi = new LoginApi();
        String urlAluno = "Api/Aluno/";

        try {
            String token = loginApi.logar();

            if (token == null) {
                return new ModelAndView("redirect:/logout");
            }

            OkHttpClient client = new OkHttpClient();
            System.out.println("GUID: " + id);
            Request request = new Request.Builder()
                    .url(baseUrl + urlAluno + id)
                    .delete()
                    .addHeader("Authorization", "Bearer" + token)
                    .build();

            Response responseApi = client.newCall(request).execute();

            return modelAndView.addObject("reponse", gson.toJson(responseApi.body().toString()));

        } catch (Exception ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("redirect:/alunos");
        }
    }

    @ModelAttribute(value = "aluno")
    public Aluno aluno() {
        return new Aluno();
    }
}
