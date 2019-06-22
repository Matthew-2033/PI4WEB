package GerenciadorDeAcademiWeb.Models;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AvaliacaoRequest {
		
    private String id;
    
    private String idAluno;

    private int idade;

    private int sexo;

    private double massa;

    private double estatura;

    private double peitoral;

    private double mediaAuxiliar;

    private double subEscapular;

    private double tricipital;

    private double biciptal;

    private double supraIliaca;

    private double abdominalDobra;

    private double coxa;

    private double panturrilha;

    private double torax;

    private double bracoDireito;

    private double bracoEsquerdo;

    private double antebracoDireito;

    private double antebracoEsquerdo;

    private double abdominalPerimetro;

    private double cintura;

    private double quadril;

    private double coxaDireita;

    private double coxaEsquerda;

    private double pernaDireita;

    private double pernaEsquerda;

    public String getIdAluno() {
        return this.idAluno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }        

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getSexo() {
        return this.sexo;
    }

    public void setSexo(int sexo) {
        switch (sexo) {
            case 1:
            this.sexo = 0;       
                break;
            case 2: 
            this.sexo = 1;            
                break;
        }        
    }

    public double getMassa() {
        return this.massa;
    }

    public void setMassa(double massa) {
        this.massa = massa;
    }

    public double getEstatura() {
        return this.estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getPeitoral() {
        return this.peitoral;
    }

    public void setPeitoral(double peitoral) {
        this.peitoral = peitoral;
    }

    public double getMediaAuxiliar() {
        return this.mediaAuxiliar;
    }

    public void setMediaAuxiliar(double mediaAuxiliar) {
        this.mediaAuxiliar = mediaAuxiliar;
    }

    public double getSubEscapular() {
        return this.subEscapular;
    }

    public void setSubEscapular(double subEscapular) {
        this.subEscapular = subEscapular;
    }

    public double getTricipital() {
        return this.tricipital;
    }

    public void setTricipital(double tricipital) {
        this.tricipital = tricipital;
    }

    public double getBiciptal() {
        return this.biciptal;
    }

    public void setBiciptal(double biciptal) {
        this.biciptal = biciptal;
    }

    public double getSupraIliaca() {
        return this.supraIliaca;
    }

    public void setSupraIliaca(double supraIliaca) {
        this.supraIliaca = supraIliaca;
    }

    public double getAbdominalDobra() {
        return this.abdominalDobra;
    }

    public void setAbdominalDobra(double abdominalDobra) {
        this.abdominalDobra = abdominalDobra;
    }

    public double getCoxa() {
        return this.coxa;
    }

    public void setCoxa(double coxa) {
        this.coxa = coxa;
    }

    public double getPanturrilha() {
        return this.panturrilha;
    }

    public void setPanturrilha(double panturrilha) {
        this.panturrilha = panturrilha;
    }

    public double getTorax() {
        return this.torax;
    }

    public void setTorax(double torax) {
        this.torax = torax;
    }

    public double getBracoDireito() {
        return this.bracoDireito;
    }

    public void setBracoDireito(double bracoDireito) {
        this.bracoDireito = bracoDireito;
    }

    public double getBracoEsquerdo() {
        return this.bracoEsquerdo;
    }

    public void setBracoEsquerdo(double bracoEsquerdo) {
        this.bracoEsquerdo = bracoEsquerdo;
    }

    public double getAntebracoDireito() {
        return this.antebracoDireito;
    }

    public void setAntebracoDireito(double antebracoDireito) {
        this.antebracoDireito = antebracoDireito;
    }

    public double getAntebracoEsquerdo() {
        return this.antebracoEsquerdo;
    }

    public void setAntebracoEsquerdo(double antebracoEsquerdo) {
        this.antebracoEsquerdo = antebracoEsquerdo;
    }

    public double getAbdominalPerimetro() {
        return this.abdominalPerimetro;
    }

    public void setAbdominalPerimetro(double abdominalPerimetro) {
        this.abdominalPerimetro = abdominalPerimetro;
    }

    public double getCintura() {
        return this.cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public double getQuadril() {
        return this.quadril;
    }

    public void setQuadril(double quadril) {
        this.quadril = quadril;
    }

    public double getCoxaDireita() {
        return this.coxaDireita;
    }

    public void setCoxaDireita(double coxaDireita) {
        this.coxaDireita = coxaDireita;
    }

    public double getCoxaEsquerda() {
        return this.coxaEsquerda;
    }

    public void setCoxaEsquerda(double coxaEsquerda) {
        this.coxaEsquerda = coxaEsquerda;
    }

    public double getPernaDireita() {
        return this.pernaDireita;
    }

    public void setPernaDireita(double pernaDireita) {
        this.pernaDireita = pernaDireita;
    }

    public double getPernaEsquerda() {
        return this.pernaEsquerda;
    }

    public void setPernaEsquerda(double pernaEsquerda) {
        this.pernaEsquerda = pernaEsquerda;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void obterPropriedadesDeUmaListaDeDto(List<AvaliacaoDTO> avaliacoesDTO, UUID idAvaliacao){
        AvaliacaoDTO avaliacaoDto = obterAvaliacaoDtoAPartirdeLista(avaliacoesDTO, idAvaliacao);

        this.id = idAvaliacao.toString();
        this.idAluno = avaliacaoDto.getIdAluno();        
        this.massa = avaliacaoDto.getMassa();
        this.estatura = avaliacaoDto.getEstatura();
        this.peitoral = avaliacaoDto.getPeitoral();
        this.mediaAuxiliar = avaliacaoDto.getMediaAuxiliar();
        this.subEscapular = avaliacaoDto.getSubEscapular();
        this.tricipital = avaliacaoDto.getTricipital();
        this.biciptal = avaliacaoDto.getBiciptal();
        this.supraIliaca = avaliacaoDto.getSupraIliaca();
        this.abdominalDobra = avaliacaoDto.getAbdominalDobra();
        this.coxa = avaliacaoDto.getCoxa();
        this.panturrilha = avaliacaoDto.getPanturrilha();
        this.torax = avaliacaoDto.getTorax();
        this.bracoDireito = avaliacaoDto.getBracoDireito();
        this.bracoEsquerdo = avaliacaoDto.getBracoEsquerdo();
        this.antebracoDireito = avaliacaoDto.getAntebracoDireito();
        this.antebracoEsquerdo = avaliacaoDto.getAntebracoEsquerdo();
        this.abdominalPerimetro = avaliacaoDto.getAbdominal();
        this.cintura = avaliacaoDto.getCintura();
        this.quadril = avaliacaoDto.getQuadril();
        this.coxaDireita = avaliacaoDto.getCoxaDireita();
        this.coxaEsquerda = avaliacaoDto.getCoxaEsquerda();
        this.pernaDireita = avaliacaoDto.getPernaDireita();
        this.pernaEsquerda = avaliacaoDto.getPernaEsquerda();        
    }

    public void atribuirIdadeAPartirDaDataDeNascimento(LocalDate dataDeNascimento){
        Period periodo = Period.between(dataDeNascimento, LocalDate.now());
        this.idade = periodo.getYears();            
    }

    public AvaliacaoDTO obterAvaliacaoDtoAPartirdeLista(List<AvaliacaoDTO> avaliacoesDTO, UUID idAvaliacao){
        return avaliacoesDTO
            .stream()
            .filter(x -> x.getiD().equals(idAvaliacao.toString()))
            .findAny().orElse(new AvaliacaoDTO());
    }
}
