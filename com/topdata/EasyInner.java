//******************************************************************************
//A Topdata Sistemas de Automação Ltda não se responsabiliza por qualquer
//tipo de dano que este software possa causar, este exemplo deve ser utilizado
//apenas para demonstrar a comunicação com os equipamentos da linha
//inner e não deve ser alterado, por este motivo ele não deve ser incluso em
//suas aplicações comerciais.
//
//Desenvolvido em Java.
//                                           Topdata Sistemas de Automação Ltda.
//******************************************************************************
package com.topdata;

/**
 * Comunicação com a DLL "EasyInner.dll" Todos os métodos estão descritos no
 * manual do desenvolvedor que acompanha a instalação da SDK
 */
public class EasyInner {

    static {
        System.loadLibrary("EasyInner");
    }

    //<editor-fold defaultstate="collapsed" desc="Constantes de Retorno EasyInner">
    public final int RET_COMANDO_OK = 0;
    public final int RET_COMANDO_ERRO = 1;
    public final int RET_PORTA_NAOABERTA = 2;
    public final int RET_PORTA_JAABERTA = 3;
    public final int RET_DLL_INNER2K_NAO_ENCONTRADA = 4;
    public final int RET_DLL_INNERTCP_NAO_ENCONTRADA = 5;
    public final int RET_DLL_INNERTCP2_NAO_ENCONTRADA = 6;
    public final int RET_ERRO_GPF = 8;
    public final int RET_TIPO_CONEXAO_INVALIDA = 9;
//</editor-fold>

    /**
     *
     * @param Inner
     * @return
     */
    public static native int SolicitarListaUsuariosComDigital(int Inner);

    /**
     *
     * @param Inner
     * @param Usuario
     * @return
     */
    public static native int SolicitarDigitalUsuario(int Inner, String Usuario);

    /**
     *
     * @param Inner
     * @param Usuario
     * @return
     */
    public static native int ReceberDigitalUsuario(int Inner, String Usuario);

    /**
     *
     * @param Inner
     * @param OnLine
     * @param Template
     * @return
     */
    public static native int ReceberDigitalUsuario(int Inner, byte[] Template, int TamResposta);
    
    /*
    ReceberRespostaRequisicaoBio
    */
    public static native int ReceberRespostaRequisicaoBio(int Inner, int[] TamResposta);

    /**
     *
     * @param Inner
     * @return
     */
    public static native int ConfigurarComportamentoIndexSearch(int Inner);

    /**
     * Habilita para utilizar usuarios com 16 digitos para biometria
     *
     * @param habilitado
     * @return
     */
    public static native int ConfigurarBioVariavel(int habilitado);

    /**
     *
     * @param Usuario
     * @return
     */
    public static native int ReceberUsuarioComDigital(byte[] Usuario);

    public static native int CMDTESTE(int i);

    /**
     * Ler giros realizados na catraca
     *
     * @param Inner
     * @param entradas
     * @param saidas
     * @param desistencias
     * @return
     */
    public static native int LerContadorGiro(int Inner, byte[] entradas, byte[] saidas, byte[] desistencias);

    /**
     * Atribui valor ao contador de giros da catraca
     *
     * @param Inner
     * @param entradas
     * @param saidas
     * @param desistencias
     * @return
     */
    public static native int AtribuirContadorGiro(int Inner, byte[] entradas, byte[] saidas, byte[] desistencias);

    /**
     *
     * @param Inner
     * @param Setor
     * @param cartao
     * @param bloco_0
     * @param bloco_1
     * @param bloco_2
     * @return
     */
    public static native int LerSmartCard(int Inner, int Setor, byte[] cartao, byte[] bloco_0, byte[] bloco_1, byte[] bloco_2);

    /**
     * Comando Novo Envia com template para o Inner Bio cadastrar no seu banco
     * de dados. O resultado do cadastro deve ser verificado no retorno da
     * função UsuarioFoiEnviado. Inner: 1 a 32 – Para comunicação serial. 1 a 99
     * – Para comunicação TCP/IP com porta variável. 1 a ... – Para comunicação
     * TCP/IP porta fixa. Template: O cadastro do usuário já contendo as duas
     * digitais e o número do usuário. É um array de bytes com o tamanho de 844
     * bytes.
     *
     * @param Inner
     * @param usuario
     * @param Template1
     * @param Template2
     * @return
     */
    public static native int EnviarDigitalUsuario(int Inner, String usuario, byte[] Template1, byte[] Template2);

    /**
     * Solicita uma digital diretaqmente do leitor do Inner
     *
     * @param Inner
     * @return
     */
    public static native int SolicitarTemplateLeitor(int Inner);

    /**
     * recebe a digital previamente solicitada
     *
     * @param Inner
     * @param OnLine
     * @param Template
     * @return
     */
    public static native int ReceberTemplateLeitor(int Inner, int OnLine, byte[] Template);

    /**
     * Configura qual será o número do cartão master que o Inner irá aceitar.
     * Válido somente para o padrão livre de cartão. Para o padrão Topdata o
     * número do master sempre é 0.
     *
     * Master 0 a 99999999999999 (Máximo de 14 dígitos) 0(Default).
     *
     * @param Master
     * @return
     */
    public static native int DefinirNumeroCartaoMaster(String Master);

    /**
     *
     * @param Sensor
     * @param Evento
     * @param Tempo
     * @return
     */
    public static native int DefinirEventoSensor(int Sensor, int Evento, int Tempo);

    /**
     * Recebe o status atual dos sensores do Inner. Essa função deverá ser
     * utilizada somente em casos muito específicos, por exemplo, quando você
     * possui um Inner Plus/NET conectado a um sensor de presença e deseja saber
     * se existe alguma pessoa naquele local Retorna o status do respectivo
     * sensor: 0 – em nível baixo (fechado) 1 – em nível alto (aberto)
     *
     * @param Inner
     * @param Sensores
     * @return
     */
    public static native int LerSensoresInner(int Inner, int[] Sensores);

    /**
     * Para usar cartoes com diferentes quantidade de digitos
     *
     * @param Digito
     * @return
     */
    public static native int InserirQuantidadeDigitoVariavel(int Digito);

    /**
     * Este comando funciona somente com o Inner no modo Off-Line. Recebe a
     * quantidade de bilhetes que há no Inner para coletar.
     *
     * @param Inner
     * @param QtdeBilhetes
     * @return
     */
    public static native int ReceberQuantidadeBilhetes(int Inner, int[] QtdeBilhetes);

    /**
     * Este comando recebe do Inner o relogio com os parametros abaixo. Tabém
     * utiliza-se para verificar se o Inner esta conectado
     *
     *
     * @param Inner
     * @param dataHora
     * @return
     */
    public static native int ReceberRelogio(int Inner, int[] dataHora);

    /**
     * aciona rele 1 para coletor
     *
     * @param Inner
     * @return
     */
    public static native int AcionarRele1(int Inner);

    /**
     * aciona rele 2 para coletor
     *
     * @param Inner
     * @return
     */
    public static native int AcionarRele2(int Inner);

    /**
     * Define qual será o tipo de conexão(meio de comunicação) que será
     * utilizada pela dll para comunicar com os Inners. Essa função deverá ser
     * chamada antes de iniciar o processo de comunicação e antes da função
     * AbrirPortaComunicacao. 0 - Comunicação serial, RS-232/485. 1 -
     * Comunicação TCP/IP com porta variável. 2 - Comunicação TCP/IP com porta
     * fixa (Default). 3 - Comunicação via modem. 4 – Comunicação via
     * TopPendrive.
     *
     * @param Tipo
     * @return
     */
    public static native int DefinirTipoConexao(int Tipo);

    /**
     * Abre a porta de comunicação. essa função deverá ser chamada antes de
     * iniciar qualquer processo de transmissão ou recepção de dados com o
     * Inner. Esta função deve ser chamada apenas uma vez e no início da
     * comunicação, e não deve ser chamada para cada Inner. Número da porta
     * serial ou TCP/IP: - Para comunicação TCP/IP o valor padrão da porta é
     * 3570 (Default). - Para comunicação Serial/Modem o valor padrão da porta é
     * 1, COM 1(Default). - Para a comunicação TopPendrive o valor é 3(Default).
     *
     * @param Porta
     * @return int
     */
    public static native int AbrirPortaComunicacao(int Porta);

    /**
     * Fecha a porta de comunicação previamente aberta, seja ela serial, Modem
     * ou TCP/IP. Em modo Off-Line normalmente é chamada após enviar/receber
     * todos os dados do Inner. Em modo On-Line é chamada somente no ecerramento
     * do software do software.
     */
    public static native void FecharPortaComunicacao();

    /**
     * Define qual padrão de cartão será utilizado pelos Inners, padrão Topdata
     * ou padrão livre. O padrão Topdata de cartão está descrito no manual dos
     * equipamentos e é utilizado somente com os Inners em modo Off-Line. No
     * padrão livre todos os dígitos do cartão são considerados como matrícula,
     * ele pode ser utilizado no modo On Line ou no modo Off Line. Ao chamar
     * essa função, a quantidade de dígitos é setada para 14. 0 - Padrão
     * Topdata. 1 - Padrão livre (Default).
     *
     * @param Padrao
     * @return int
     */
    public static native int DefinirPadraoCartao(int Padrao);

    /**
     * Faz com que o Inner emita um bip curto(aviso sonoro).
     *
     * @param Inner
     * @return
     */
    public static native int AcionarBipCurto(int Inner);

    /**
     * Faz com que o Inner emita um bip long(aviso sonoro).
     *
     * @param Inner
     * @return
     */
    public static native int AcionarBipLongo(int Inner);

    /**
     * Testa a comunicação com o Inner, também utilizado para efetuar a conexão
     * com o Inner. Para efetuar a conexão com o Inner, essa função deve ser
     * executada em um loop até retornar 0(zero), executado com sucesso.
     *
     * @param Inner
     * @return
     */
    public static native int Ping(int Inner);

    /**
     * Liga a luz emitida pelo display do Inner. Essa função não deve ser
     * utilizada nas catracas.
     *
     * @param Inner
     * @return
     */
    public static native int LigarBackLite(int Inner);

    /**
     * Desliga a luz emitida pelo display do Inner. Essa função não deve ser
     * utilizada nas catracas.
     *
     * @param Inner
     * @return
     */
    public static native int DesligarBackLite(int Inner);

    /**
     * Permite que os dados sejam inseridos no Inner através do teclado do
     * equipamento. Habilitando o parâmetro ecoar, o teclado irá ecoar
     * asteriscos no display do Inner. Habilita: 0 - Desabilita o teclado
     * (Default). 1 - Habilita o teclado. Ecoar: 0 – Ecoa o que é digitado no
     * display do Inner (Default). 1 – Ecoa asteriscos no display do Inner.
     *
     * @param Habilita
     * @param Ecoar
     * @return
     */
    public static native int HabilitarTeclado(int Habilita, int Ecoar);

    /**
     * Configura como irá funcionar o acionamento(rele) 1 do Inner, e por quanto
     * tempo ele será acionado. Função: 0 – Não utilizado(Default). 1 – Aciona
     * ao registrar uma entrada ou saída. 2 – Aciona ao registrar uma entrada. 3
     * – Aciona ao registrar uma saída. 4 – Está conectado a uma sirene(Ver as
     * funções de sirene). 5 – Utilizado para a revista de usuários(Ver a função
     * DefinirPorcentagemRevista). 6 – Catraca com a saída liberada. 7 – Catraca
     * com a entrada liberada 8 – Catraca liberada nos dois sentidos. 9 –
     * Catraca liberada nos dois sentidos e a marcação(registro) é gerada de
     * acordo com o sentido do giro.</param>
     * Tempo - 0 a 50 segundos. 0(Default).
     *
     * @param Funcao
     * @param Tempo
     * @return
     */
    public static native int ConfigurarAcionamento1(int Funcao, int Tempo);

    /**
     * Configura como irá funcionar o acionamento(rele) 2 do Inner, e por quanto
     * tempo ele será acionado. Função: 0 – Não utilizado(Default). 1 – Aciona
     * ao registrar uma entrada ou saída. 2 – Aciona ao registrar uma entrada. 3
     * – Aciona ao registrar uma saída. 4 – Está conectado a uma sirene(Ver as
     * funções de sirene). 5 – Utilizado para a revista de usuários(Ver a função
     * DefinirPorcentagemRevista). 6 – Catraca com a saída liberada. 7 – Catraca
     * com a entrada liberada 8 – Catraca liberada nos dois sentidos. 9 –
     * Catraca liberada nos dois sentidos e a marcação(registro) é gerada de
     * acordo com o sentido do giro.</param>
     * Tempo - 0 a 50 segundos. 0(Default).
     *
     * @param Funcao
     * @param Tempo
     * @return
     */
    public static native int ConfigurarAcionamento2(int Funcao, int Tempo);

    /**
     * Configura o tipo do leitor que o Inner está utilizando, se é um leitor de
     * código de barras, magnético ou proximidade. Tipo: 0 – Leitor de código de
     * barras(Default). 1 – Leitor Magnético. 2 – Leitor proximidade AbaTrack2.
     * 3 – Leitor proximidade Wiegand e Wiegand Facility Code. 4 – Leitor
     * proximidade Smart Card.
     *
     * @param Tipo
     * @return
     */
    public static native int ConfigurarTipoLeitor(int Tipo);

    /**
     * Configura as operações que o leitor irá executar. Se irá registrar os
     * dados somente como entrada independente do sentido em que o cartão for
     * passado, somente como saída ou como entrada e saída. Operação: 0 – Leitor
     * desativado(Default). 1 – Somente para entrada. 2 – Somente para saída. 3
     * – Entrada e saída. 4 – Entrada e saída invertidas
     *
     * @param Operacao
     * @return
     */
    public static native int ConfigurarLeitor1(int Operacao);

    /**
     * Configura as operações que o leitor irá executar. Se irá registrar os
     * dados somente como entrada independente do sentido em que o cartão for
     * passado, somente como saída ou como entrada e saída. Operação: 0 – Leitor
     * desativado(Default). 1 – Somente para entrada. 2 – Somente para saída. 3
     * – Entrada e saída. 4 – Entrada e saída invertidas
     *
     * @param Operacao
     * @return
     */
    public static native int ConfigurarLeitor2(int Operacao);

    /**
     * Define a quantidade de dígitos dos cartões a serem lidos pelo Inner.
     * Quantidade - 1 a 16 dígitos. 14(Default).
     *
     * @param Quantidade
     * @return
     */
    public static native int DefinirQuantidadeDigitosCartao(int Quantidade);

    /**
     * Configura o Inner para registrar as tentativa de acesso negado. O Inner
     * irá rgistrar apenas os acessos negados em relação a lista de acesso
     * configurada para o modo Off-Line, ver as funções DefinirTipoListaAcesso e
     * ColetarBilhete. TipoRegistro: 0 – Não registrar o acesso negado. 1 –
     * Apenas o acesso negado. 2 – Falha na validação da digital. 3 – Acesso
     * negado e falha na validação da digital.
     *
     * @param TipoRegistro
     * @return
     */
    public static native int RegistrarAcessoNegado(int TipoRegistro);

    /**
     * Define qual será o tipo do registro realizado pelo Inner ao aproximar um
     * cartão do tipo proximidade no leitor do Inner, sem que o usuário tenha
     * pressionado a tecla entrada, saída ou função. Funcao: 0 –
     * Desablitado(Default). 1 a 9 – Registrar como uma função do teclado do
     * Inner. 10 – Registrar sempre como entrada. 11 – Registrar sempre como
     * saída. 12 – Libera a catraca nos dois sentidos e registra o bilhete
     * conforme o sentido giro.
     *
     * @param Funcao
     * @return
     */
    public static native int DefinirFuncaoDefaultLeitoresProximidade(int Funcao);

    /**
     * Habilita os leitores wiegand para o primeiro leitor e o segundo leitor do
     * Inner, e configura se o segundo leitor irá exibir as mensagens
     * configuradas. Habilita: 0 – Não habilita o segundo leitor como
     * wiegand(Default). 1 – Habilita o segundo leitor como wiegand.
     * ExibirMensagem: 0 – Não exibe mensagem segundo leitor(Default). 1 – Exibe
     * mensagem segundo leitor.
     *
     * @param Habilita
     * @param ExibirMensagem
     * @return
     */
    public static native int ConfigurarWiegandDoisLeitores(int Habilita, int ExibirMensagem);

    /**
     * Configura o tipo de registro que será associado a uma marcação, quando
     * for inserido o dedo no Inner bio sem que o usuário tenha definido se é um
     * entrada, saída, função, etc. Funcao: 0 – desabilitada(Default). 1 a 9 –
     * funções de 1 a 9. 10 – entrada. 11 – saída. 12 – libera catraca para os
     * dois lados e registra bilhete conforme o giro.
     *
     * @param Funcao
     * @return
     */
    public static native int DefinirFuncaoDefaultSensorBiometria(int Funcao);

    /**
     * Envia o buffer interno da dll que contém todas as configurações das
     * funções anteriores para o Inner, após o envio esse buffer é limpo sendo
     * necessário chamar novamentes as funções acima para reconfigurá-lo. Inner:
     * 1 a 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP com
     * porta variável. 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int EnviarConfiguracoes(int Inner);

    /**
     * Configura o relógio(data/hora) do Inner. Inner: 1 a 32 – Para comunicação
     * serial. 1 a 99 – Para comunicação TCP/IP com porta variável. 1 a ... –
     * Para comunicação TCP/IP porta fixa. Dia - 1 a 31 Mes - 1 a 12. Ano - 0 a
     * 99 Hora - 0 a 23 Minuto - 0 a 59 Segundo - 0 a 59
     *
     * @param Inner
     * @param Dia
     * @param Mes
     * @param Ano
     * @param Hora
     * @param Minuto
     * @param Segundo
     * @return
     */
    public static native int EnviarRelogio(int Inner, int Dia, int Mes, int Ano, int Hora, int Minuto, int Segundo);

    /**
     * Solicita a versão do firmware do Inner e dados como o Idioma, se é uma
     * versão especial. Inner: Número do Inner desejado. Linha: 01 – Inner Plus.
     * 02 – Inner Disk. 03 – Inner Verid. 06 – Inner Bio. 07 – Inner NET.
     * Variacao - Depende da versão, existe somente em versões customizadas.
     * VersaoAlta - 00 a 99 VersaoBaixa - 00 a 99 VersaoSufixo - Indica o idioma
     * do firmware: 01 – Português. 02 – Espanhol. 03 – Inglês. 04 – Francês.
     *
     * @param Inner
     * @param Versao
     * @return
     */
    public static native int ReceberVersaoFirmware(int Inner, int[] Versao);

    /**
     * Apaga o buffer com a lista de horários de acesso e envia automaticamente
     * para o Inner. Inner: 1 a 32 – Para comunicação serial. 1 a 99 – Para
     * comunicação TCP/IP com porta variável. 1 a ... – Para comunicação TCP/IP
     * porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int ApagarHorariosAcesso(int Inner);

    /**
     * Insere no buffer da dll um horário de acesso. O Inner possui uma tabela
     * de 100 horários de acesso, para cada horário é possível definir 4 faixas
     * de acesso para cada dia da semana. Horario - 1 a 100 – Número da tabela
     * de horários. DiaSemana - 1 a 7 – Dia da semana a qual pertence a faixa de
     * horário. FaixaDia - 1 a 4 – Para cada dia da semana existem 4 faixas de
     * horário. Hora - 0 a 23 Minuto - 0 a 59
     *
     * @param Horario
     * @param DiaSemana
     * @param FaixaDia
     * @param Hora
     * @param Minuto
     * @return
     */
    public static native int InserirHorarioAcesso(int Horario, int DiaSemana, int FaixaDia, int Hora, int Minuto);

    /**
     * Envia para o Inner o buffer com a lista de horários de acesso, após
     * executar o comando o buffer é limpo pela dll automaticamente. Inner: 1 a
     * 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta
     * variável. 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int EnviarHorariosAcesso(int Inner);

    /**
     * Limpar o buffer com a lista de usuários cadastrados e envia
     * automaticamente para o Inner. Inner: 1 a 32 – Para comunicação serial. 1
     * a 99 – Para comunicação TCP/IP com porta variável. 1 a ... – Para
     * comunicação TCP/IP porta fixa.
     *
     *
     * @param Inner
     * @return
     */
    public static native int ApagarListaAcesso(int Inner);

    /**
     * Insere no buffer da dll um usuário da lista e a qual horário de acesso
     * ele está associado. Os horários já deverão ter sido cadastrados através
     * das funções InserirHorarioAcesso e enviados através da função
     * EnviarHorariosAcesso para a lista ter o efeito correto. Cartao - 1 a ...
     * – Dependo do padrão de cartão definido e da quantidade de dígitos
     * definida. Horario - 1 a 100 – Número do horário já cadastrado no Inner.
     * 101 – Acesso sempre liberado para o usuário. 102 – Acesso sempre negado
     * para o usuário.
     *
     * @param Cartao
     * @param Horario
     * @return
     */
    public static native int InserirUsuarioListaAcesso(String Cartao, int Horario);

    /**
     * Envia o buffer com os usuários da lista de acesso para o Inner, após
     * executar o comando o buffer é limpo pela dll automaticamente. Inner: 1 a
     * 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta
     * variável. 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int EnviarListaAcesso(int Inner);

    /**

     *
     * @param Tipo
     * @return
     */
    public static native int DefinirTipoListaAcesso(int Tipo);

    /**
     * Testa comunicação com o Inner e mantém o Inner em OnLine quando a mudança
     * automática está configurada. Especialmente indicada para a verificação da
     * conexão em comunicação TCP/IP.
     *
     * @param Inner
     * @return
     */
    public static native int PingOnLine(int Inner);

    /**
     * Insere um horário de toque de sirene e configura em quais dias da semana
     * esses horário irão tocar. É possível inserir no máximo 100 horários para
     * a sirene. Hora - 0 a 23 Minuto - 0 a 59 Segunda: 0 – Desabilita o toque
     * nesse dia. 1 – Habilita o toque nesse dia. Terca: 0 – Desabilita o toque
     * nesse dia. 1 – Habilita o toque nesse dia. Quarta: 0 – Desabilita o toque
     * nesse dia. 1 – Habilita o toque nesse dia. Quinta: 0 – Desabilita o toque
     * nesse dia. 1 – Habilita o toque nesse dia. Sexta: 0 – Desabilita o toque
     * nesse dia. 1 – Habilita o toque nesse dia. Sabado: 0 – Desabilita o toque
     * nesse dia. 1 – Habilita o toque nesse dia. DomingoFeriado: 0 – Desabilita
     * o toque nesse dia. 1 – Habilita o toque nesse dia.
     *
     * @param Hora
     * @param Minuto
     * @param Segunda
     * @param Terca
     * @param Quarta
     * @param Quinta
     * @param Sexta
     * @param Sabado
     * @param DomingoFeriado
     * @return
     */
    public static native int InserirHorarioSirene(int Hora, int Minuto, int Segunda, int Terca, int Quarta, int Quinta, int Sexta, int Sabado, int DomingoFeriado);

    /**
     * Envia o buffer com os horário de sirene cadastrados para o Inner. Após
     * executar a função o buffer é limpo automaticamente pela dll. Inner: 1 a
     * 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta
     * variável. 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int EnviarHorariosSirene(int Inner);

    /**

     *
     * @param Inner
     * @return
     */
    public static native int LiberarCatracaDoisSentidos(int Inner);
    /**/

 
    public static native int LiberarCatracaEntrada(int Inner);
    /**/

    /**
     * Libera a catraca no sentido de saída padrão do Inner, para o usuário
     * poder efetuar o giro na catraca. Em modo On-Line, na função
     * ReceberDadosOnLine o valor retornado no parâmetro “Complemento�? será do
     * tipo saída. Essa função deve ser utilizada somente com Inners Catraca.
     *
     * @param Inner
     * @return
     */
    public static native int LiberarCatracaSaida(int Inner);
    /**/

    /**
     
     *
     * @param Inner
     * @return
     */
    public static native int LiberarCatracaEntradaInvertida(int Inner);
    /**/

    /**
     
     *
     * @param Inner
     * @return
     */
    public static native int LiberarCatracaSaidaInvertida(int Inner);
    /**/

    /**

     *
     * @return
     */
    public static native int ConfigurarInnerOffLine();
    /**/

    /**

     *
     * @param ExibirData
     * @param Mensagem
     * @return
     */
    public static native int DefinirMensagemEntradaOffLine(int ExibirData, String Mensagem);
    /**/

    /**
     * Configura a mensagem a ser exibida quando o usuário passar o cartão no
     * sentido de saída do Inner. Caso a mensagem passe de 32 caracteres a DLL
     * irá utilizar os primeiros 32 caracteres. O Inner não aceita caracteres
     * com acentuação, padrão UNICODE ou padrão ANSI. O Inner aceita apenas os
     * caracteres do padrão ASCII. ExibirData: 0 – Não exibe a data/hora na
     * linha superior do display. 1 – Exibe a data/hora na linha superior do
     * display(Default). Mensagem String com a mensagem a ser exibida. Caso
     * esteja exibindo a data/hora o tamanho da mensagem passa a ser 16 ao invés
     * de 32. Caso seja passado uma string vazia o Inner exibirá a mensagem em
     * branco no display. Entrada OK(Default).
     *
     * @param ExibirData
     * @param Mensagem
     * @return
     */
    public static native int DefinirMensagemSaidaOffLine(int ExibirData, String Mensagem);
    /**/

    /**
     * Configura a mensagem a ser exibida pelo Inner quando ele estiver ocioso.
     * Caso a mensagem passe de 32 caracteres a DLL irá utilizar os primeiros 32
     * caracteres. O Inner não aceita caracteres com acentuação, padrão UNICODE
     * ou padrão ANSI. O Inner aceita apenas os caracteres do padrão ASCII.
     * ExibirData: 0 – Não exibe a data/hora na linha superior do display. 1 –
     * Exibe a data/hora na linha superior do display(Default). Mensagem: String
     * com a mensagem a ser exibida. Caso esteja exibindo a data/hora o tamanho
     * da mensagem passa a ser 16 ao invés de 32. Caso seja passado uma string
     * vazia o Inner exibirá a mensagem em branco no display. Passe o
     * cartão(Default).
     *
     * @param ExibirData
     * @param Mensagem
     * @return
     */
    public static native int DefinirMensagemPadraoOffLine(int ExibirData, String Mensagem);
    /**/

    /**
     * Envia o buffer com todas as mensagens off line configuradas
     * anteriormente, para o Inner. Após executar a função, o buffer com as
     * mensagens é limpo automaticamente pela dll. Inner: 1 a 32 – Para
     * comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta variável.
     * 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int EnviarMensagensOffLine(int Inner);
    /**/

    /**
     * Habilita/Desabilita a mudanção automática do modo OffLine do Inner para
     * OnLine e viceversa. Configura o tempo após a comunicação ser interrompida
     * que está mudança irá ocorrer. Habilita: 0 – Desabilita a
     * mudança(Default). 1 – Habilita a mudança. 2 – Habilita a mudança
     * automática para o modo OnLine TCP com Ping, onde o Inner precisa receber
     * o comando PingOnLine para manter-se OnLine. Tempo - Tempo em segundos
     * para ocorrer a mudança. 1 a 50.
     *
     * @param Habilita
     * @param Tempo
     * @return
     */
    public static native int HabilitarMudancaOnLineOffLine(int Habilita, int Tempo);

    /**
     * Configura as formas de entradas de dados para quando o Inner mudar para o
     * modo Off-Line. Para aplicações com biometria verifique a próxima função
     * “DefinirEntradasMudançaOffLineComBiometria�?. Teclado: 0 – Não aceita
     * dados pelo teclado. 1 – Aceita dados pelo teclado. Leitor1: 0 –
     * desativado 1 – somente para entrada 2 – somente para saída 3 – entrada e
     * saída 4 – saída e entrada Leitor2: 0 – desativado 1 – somente para
     * entrada 2 – somente para saída 3 – entrada e saída 4 – saída e entrada
     * Catraca - 0 – reservado para uso futuro.
     *
     * @param Teclado
     * @param Leitor1
     * @param Leitor2
     * @param Catraca
     * @return
     */
    public static native int DefinirEntradasMudancaOffLine(int Teclado, int Leitor1, int Leitor2, int Catraca);

    /**
     * Configura a mensagem padrão exibido pelo Inner quando entrar para on line
     * após uma queda para off line. 0 – Não exibe a data/hora na linha superior
     * do display. 1 – Exibe a data/hora na linha superior do display. String
     * com a mensagem a ser exibida. Caso esteja exibindo a data/hora o tamanho
     * da mensagem passa a ser 16 ao invés de 32. Caso seja passado uma string
     * vazia o Inner não exibirá a mensagem no display
     *
     * @param ExibirData
     * @param Mensagem
     * @return
     */
    public static native int DefinirMensagemPadraoMudancaOnLine(int ExibirData, String Mensagem);

    /**

     *
     * @param ExibirData
     * @param Mensagem
     * @return
     */
    public static native int DefinirMensagemPadraoMudancaOffLine(int ExibirData, String Mensagem);
    /**/

    /**
 
     * porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int EnviarConfiguracoesMudancaAutomaticaOnLineOffLine(int Inner);

    /**
     * Configura a mensagem a ser exibida quando o usuário passar o cartão
     * utilizando uma das funções do Inner(de 0 a 9) e a habilita ou desabilita
     * essas funções. Caso a mensagem passe de 32 caracteres a DLL irá utilizar
     * os primeiros 32 caracteres. O Inner não aceita caracteres com acentuação,
     * padrão UNICODE ou padrão ANSI. O Inner aceita apenas os caracteres do
     * padrão ASCII. Mensagem: String com a mensagem a ser exibida. Caso esteja
     * exibindo a data/hora o tamanho da mensagem passa a ser 16 ao invés de 32.
     * Caso seja passado uma string vazia o Inner não exibirá a mensagem no
     * display. Funcao - 0 a 9. Habilitada: 0 – Desabilita a função do
     * Inner(Default). 1 – Habilita a função do Inner.
     *
     * @param Mensagem
     * @param Funcao
     * @param Habilitada
     * @return
     */
    public static native int DefinirMensagemFuncaoOffLine(String Mensagem, int Funcao, int Habilitada);

    /**
     * Coleta um bilhete Off-Line que está armazenado na memória do Inner, os
     * dados do bilhete são retornados por referência nos parâmetros da função.
     * Ates de chamar esta função pela primeira vez é preciso chamar
     * obrigatoriamente as funções DefinirPadraoCartao e
     * DefinirQuantidadeDigitosCartao nessa ordem para que o número do cartão
     * seja calculado de forma correta. Inner: 1 a 32 – Para comunicação serial.
     * 1 a 99 – Para comunicação TCP/IP com porta variável. 1 a ... – Para
     * comunicação TCP/IP porta fixa. Tipo - Tipo da marcação registrada. 0 a 9
     * – Funções registradas pelo cartão. 10 – Entrada pelo cartão. 11 – Saída
     * pelo cartão. 12 – Tentativa de entrada negada pelo cartão. 13 – Tentativa
     * de saída negada pelo cartão. 100 a 109 – Funções registradas pelo
     * teclado. 110 – Entrada pelo teclado. 111 – Saída pelo teclado. 112 –
     * Tentativa de entrada negada pelo teclado. 113 – Tentativa de saída negada
     * pelo teclado. Dia - 1 a 31 Mes - 1 a 12 Ano - 0 a 99 Hora - 0 a 23 Minuto
     * - 0 a 59 Cartao - Número do cartão do usuário.
     *
     * @param Inner
     * @param Dados
     * @param Cartao
     * @return
     */
    public static native int ColetarBilhete(int Inner, int[] Dados, StringBuffer Cartao);

    /**
     * Prepara o Inner para trabalhar no modo On-Line, porém essa função ainda
     * não envia essa informação para o equipamento.
     *
     * @return
     */
    public static native int ConfigurarInnerOnLine();

    /**
     * Configura o Inner para enviar as informações de data/hora nos bilhete on
     * line, esses dados serão retornados nos parâmetros da função
     * ReceberDadosOnLine. Recebe: 0 – Não receber a data/hora do
     * bilhete(Default). 1 – Recebe a data/hora do bilhete.
     *
     * @param Recebe
     * @return
     */
    public static native int ReceberDataHoraDadosOnLine(int Recebe);

    /**
     * Envia para o Inner a mensagem padrão(fixa) que será sempre exibida pelo
     * Inner. Essa mensagem é exibida enquanto o Inner estiver ocioso. Caso a
     * mensagem passe de 32 caracteres a DLL irá utilizar os primeiros 32
     * caracteres. O Inner não aceita caracteres com acentuação, padrão UNICODE
     * ou padrão ANSI. O Inner aceita apenas os caracteres do padrão ASCII.
     * Inner: 1 a 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP
     * com porta variável. 1 a ... – Para comunicação TCP/IP porta fixa.
     * ExibirData: 0 – Não exibe a data/hora na linha superior do display. 1 –
     * Exibe a data/hora na linha superior do display. Mensagem - String com a
     * mensagem a ser exibida. Caso esteja exibindo a data/hora o tamanho da
     * mensagem passa a ser 16 ao invés de 32. Caso seja passado uma string
     * vazia o Inner exibirá a mensagem em branco no display.
     *
     * @param Inner
     * @param ExibirData
     * @param Mensagem
     * @return
     */
    public static native int EnviarMensagemPadraoOnLine(int Inner, int ExibirData, String Mensagem);

    /**
     * Configura as formas de entrada de dados do Inner no modo OnLine. Cada vez
     * que alguma informação for recebida no modo OnLine através da função
     * ReceberDadosOnLine, a função EnviarFormasEntradasOnLine deverá ser
     * chamada novamente para reconfigurar o Inner. Inner: 1 a 32 – Para
     * comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta variável.
     * 1 a ... – Para comunicação TCP/IP porta fixa QtdeDigitosTeclado - 0 a 20
     * dígitos. EcoTeclado: 0 – para não 1 – para sim 2 – ecoar FormaEntrada: 0
     * – não aceita entrada de dados 1 – aceita teclado 2 – aceita leitura no
     * leitor 1 3 – aceita leitura no leitor 2 4 – teclado e leitor 1 5 –
     * teclado e leitor 2 6 – leitor 1 e leitor 2 7 – teclado, leitor 1 e leitor
     * 2 10 – teclado + verificação biométrica 11 – leitor1 + verificação
     * biométrica 12 – teclado + leitor1 + verificação biométrica 13 – leitor1
     * com verificação biométrica + leitor2 sem verificação biométrica 14 –
     * leitor1 com verificação biométrica + leitor2 sem verificação biométrica +
     * teclado sem verificação biométrica 100 – Leitor 1 + Identificação
     * Biométrica (sem Verificação) 101 – Leitor 1 + Teclado + Identificação
     * Biométrica (sem Verificação) 102 – Leitor 1 + Leitor 2 + Identificação
     * Biométrica (sem Verificação) 103 – Leitor 1 + Leitor 2 + Teclado +
     * Identificação Biométrica (sem Verificação) 104 – Leitor 1 invertido +
     * Identificação Biométrica (sem Verificação) 105 – Leitor 1 invertido +
     * Teclado + Identificação Biométrica (sem Verificação) TempoTeclado - 1 a
     * 50 PosicaoCursorTeclado - 1 a 32
     *
     * @param Inner
     * @param QtdeDigitosTeclado
     * @param EcoTeclado
     * @param FormaEntrada
     * @param TempoTeclado
     * @param PosicaoCursorTeclado
     * @return
     */
    public static native int EnviarFormasEntradasOnLine(int Inner, int QtdeDigitosTeclado, int EcoTeclado, int FormaEntrada, int TempoTeclado, int PosicaoCursorTeclado);

    /**
     * Coleta um bilhete OnLine, caso o usuário tenha passado ou digitado algum
     * cartão no Inner retorna as informações do cartão nos parâmetros da
     * função. Para que a data/hora do bilhete OnLine seja retornada, o Inner
     * deverá ter sido previamente configurado através da função
     * ReceberDataHoraDadosOnLine. Inner: 1 a 32 – Para comunicação serial. 1 a
     * 99 – Para comunicação TCP/IP com porta variável. 1 a ... – Para
     * comunicação TCP/IP porta fixa. Origem - Origem dos dados recebidos. 1 –
     * via teclado 2 – via leitor 1 3 – via leitor 2 4 – sensor da
     * catraca(obsoleto) 5 – fim do tempo de acionamento 6 – giro da catraca
     * Topdata (sensor ótico) 7 – Urna (catraca Millenium) 8 – Evento no Sensor
     * 1 9 – Evento no Sensor 2 10 – Evento no Sensor 3 Complemento -
     * Informações adicionais sobre os dados recebidos. 0 – saída (com cartão) 1
     * – entrada (com cartão) 35 – # via teclado (1° tecla) 42 – * via teclado
     * (1° tecla) 65 – “Função�? via teclado 66 – “Entrada�? via teclado 67 –
     * “Saída�? via teclado 255 – Inseriu todos os dígitos permitidos pelo
     * teclado. Evento do Sensor 0/1 – Nível atual do sensor Cartao - Número do
     * cartão recebido. Dia - 1 a 31. Mes - 1 a 12. Ano - 0 a 99 Hora - 0 a 23
     * Minuto - 0 a 59 Segundo - 0 a 59
     *
     * @param Inner
     * @param Dados
     * @param Cartao
     * @return
     */
    public static native int ReceberDadosOnLine(int Inner, int[] Dados, StringBuffer Cartao);

    /**
     * Configura as formas de entrada dos dados quando o Inner voltar para o
     * modo On Line após uma queda para OffLine. Entrada: 0 – Não aceita entrada
     * de dados. 1 – Aceita teclado. 2 – Aceita leitor 1. 3 – Aceita leitor 2. 4
     * – Teclado e leitor 1. 5 – Teclado e leitor 2. 6 – Leitor 1 e leitor 2. 7
     * – Teclado, leitor 1 e 2. 8 – Sensor da catraca.
     *
     * @param Entrada
     * @return
     */
    public static native int DefinirEntradasMudancaOnLine(int Entrada);

    /**
     * Configura o teclado para quando o Inner voltar para OnLine após uma queda
     * para OffLine. Digitos - 0 a 20 dígitos. EcoDisplay: 0 – para não ecoar 1
     * – para sim 2 – ecoar '*' Tempo - 1 a 50. PosicaoCursor - 1 a 32.
     *
     * @param Digitos
     * @param EcoDisplay
     * @param Tempo
     * @param PosicaoCursor
     * @return
     */
    public static native int DefinirConfiguracaoTecladoOnLine(int Digitos, int EcoDisplay, int Tempo, int PosicaoCursor);

    /**

     *
     * @param Inner
     * @param HabilitaIdentificacao
     * @param HabilitaVerificacao
     * @return
     */
    public static native int ConfigurarBio(int Inner, int HabilitaIdentificacao, int HabilitaVerificacao);

    /**

     *
     * @param Inner
     * @param OnLine
     * @return
     */
    public static native int ResultadoConfiguracaoBio(int Inner, int OnLine);

    /**
     * Insere o número do cartão na lista de usuários sem digital do Inner bio.
     * Este número ficara armazenado em um buffer interno dentro da dll e
     * somente será enviado para o Inner após a chamada a função
     * EnviarListaUsuariosSemDigitalBio. O número máximo de dígitos para o
     * cartão é 10, caso os cartões tenham mais de 10 dígitos, utilizar os 10
     * dígitos menos significativos do cartão. Cartao - 1 a 9999999999 – Número
     * do cartão do usuário.
     *
     * @param Cartao
     * @return
     */
    public static native int IncluirUsuarioSemDigitalBio(String Cartao);
    public static native int IncluirUsuarioSemDigitalBioInnerAcesso(String Cartao);

    /**
     * Solicita a versão do firmware da placa do Inner Bio, a placa que armazena
     * as digitais. Inner: 1 a 32 – Para comunicação serial. 1 a 99 – Para
     * comunicação TCP/IP com porta variável. 1 a ... – Para comunicação TCP/IP
     * porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int SolicitarVersaoBio(int Inner);

    /**
     * Envia o buffer com a lista de usuários sem digital para o Inner. Após a
     * execução do comando, o buffer é limpo pela dll. Inner: 1 a 32 – Para
     * comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta variável.
     * 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int EnviarListaUsuariosSemDigitalBio(int Inner);
    /**
     * Envia o buffer com a lista de usuários sem digital para o Inner. Após a
     * execução do comando, o buffer é limpo pela dll. Inner: 1 a 32 – Para
     * comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta variável.
     * 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @param QtdDigitos
     * @return
     */
    public static native int EnviarListaUsuariosSemDigitalBioVariavel(int Inner, int QtdDigitos);

    /**
     * Solicita o modelo do Inner bio. Para receber o resultado dessa operação
     * você deverá chamar a função ReceberModeloBio enquanto o retorno for
     * processando a operação. Inner: 1 a 32 – Para comunicação serial. 1 a 99 –
     * Para comunicação TCP/IP com porta variável. 1 a ... – Para comunicação
     * TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int SolicitarModeloBio(int Inner);

    /**
     * Retorna o resultado do comando SolicitarModeloBio, o modelo do Inner Bio
     * é retornado por referência no parâmetro da função. Inner: 1 a 32 – Para
     * comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta variável.
     * 1 a ... – Para comunicação TCP/IP porta fixa. OnLine: 0 – O comando foi
     * utilizado com o Inner em OffLine. 1 – O comando foi utilizado com o Inner
     * em OnLine Modelo: 2 – Bio Light 100 usuários. 4 – Bio 1000/4000 usuários.
     * 255 – Versão desconhecida.
     *
     * @param Inner
     * @param OnLine
     * @param Modelo
     * @return
     */
    public static native int ReceberModeloBio(int Inner, int OnLine, int[] Modelo);

    /**
     * Retorna o resultado do comando SolicitarVersaoBio, a versão do Inner Bio
     * é retornado por referência nos parâmetros da função. Inner: 1 a 32 – Para
     * comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta variável.
     * 1 a ... – Para comunicação TCP/IP porta fixa. OnLine: 0 – O comando foi
     * utilizado com o Inner em OffLine. 1 – O comando foi utilizado com o Inner
     * em OnLine VersaoAlta - Parte alta da versão. VersaoBaixa - Parte baixa da
     * versão.
     *
     * @param Inner
     * @param OnLine
     * @param Versao
     * @return
     */
    public static native int ReceberVersaoBio(int Inner, int OnLine, int[] Versao);

    /**
     * Define que o Inner utilizado no momento é um Inner bio light ao invés de
     * um Inner bio 1000/4000. Essa função deverá ser chamada sempre que
     * necessário antes das funções SolicitarUsuarioCadastradoBio,
     * SolicitarExclusaoUsuario, InserirUsuarioLeitorBio e
     * FazerVerificacaoBiometricaBio. Light: 1 – É um Inner bio light 0 – É um
     * Inner bio 1000/4000(valor default)
     *
     * @param Light
     */
    public static native void SetarBioLight(int Light);

    /**
     *
     * @param habilitado
     */
    public static native void SetarBioVariavel(int habilitado);

    /**
     * Solicita para o Inner Bio inserir um usuário diretamente pelo leitor
     * biométrico. O leitor irá acender a luz vermelho e após o usuário inserir
     * a digital, automaticamente o usuário será cadastrado no nner bio com o
     * número do cartão passado no parâmetro Usuário. Inner: 1 a 32 – Para
     * comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta variável.
     * 1 a ... – Para comunicação TCP/IP porta fixa. Tipo: 0 – para solicitar o
     * primeiro template 1 – para solicitar o segundo template (mesmo dedo) e
     * salvar. 2 – para solicitar o segundo template (outro dedo) e salvar.
     * Usuario - Número do cartão que o usuário terá.
     *
     * @param Inner
     * @param Tipo
     * @param Usuario
     * @return
     */
    public static native int InserirUsuarioLeitorBio(int Inner, int Tipo, String Usuario);

    /**
     * Retorna o resultado do comando InserirUsuarioLeitorBio. Se o retorno for
     * igual a 0 é porque o usuário foi cadastrado com sucesso. Inner: 1 a 32 –
     * Para comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta
     * variável. 1 a ... – Para comunicação TCP/IP porta fixa. OnLine: 0 – O
     * comando foi utilizado com o Inner em OffLine. 1 – O comando foi utilizado
     * com o Inner em OnLine.
     *
     * @param Inner
     * @param OnLine
     * @return
     */
    public static native int ResultadoInsercaoUsuarioLeitorBio(int Inner, int OnLine);

    /**
     * Solicita para o Inner bio excluir o cadastro do usuário desejado. O
     * Retorno da exclusão é verificado através da função UsuarioFoiExcluido
     * Inner: 1 a 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP
     * com porta variável. 1 a ... – Para comunicação TCP/IP porta fixa. Usuario
     * - Número do cartão do usuário cadastrado.
     *
     * @param Inner
     * @param Usuario
     * @return
     */
    public static native int SolicitarExclusaoUsuario(int Inner, String Usuario);

    /**
     * Retorna o resultado do comando SolicitarExclusaoUsuario, se o retorno da
     * função for igual a 0 é porque o usuário foi excluído com sucesso. Inner:
     * 1 a 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP com
     * porta variável. 1 a ... – Para comunicação TCP/IP porta fixa. OnLine: 0 –
     * O comando foi utilizado com o Inner em OffLine. 1 – O comando foi
     * utilizado com o Inner em OnLine.
     *
     * @param Inner
     * @param OnLine
     * @return
     */
    public static native int UsuarioFoiExcluido(int Inner, int OnLine);

    /**
     * Solicita do Inner Bio, o template com as duas digitais do Usuario
     * desejado. Inner: 1 a 32 – Para comunicação serial. 1 a 99 – Para
     * comunicação TCP/IP com porta variável. 1 a ... – Para comunicação TCP/IP
     * porta fixa. Usuario - Número do cartão do usuário cadastrado.
     *
     * @param Inner
     * @param Usuario
     * @return
     */
    public static native int SolicitarUsuarioCadastradoBio(int Inner, String Usuario);

    /**
     * Retorna o resultado do comando SolicitarUsuarioCadastradoBio e o template
     * com as duas digitais do usuário cadastrado no Inner Bio. O template é
     * retornado por referência nos parâmetros da função. Inner: 1 a 32 – Para
     * comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta variável.
     * 1 a ... – Para comunicação TCP/IP porta fixa. OnLine: 0 – O comando foi
     * utilizado com o Inner em OffLine. 1 – O comando foi utilizado com o Inner
     * em OnLine Template: Cadastro do usuário com as duas digitais, o dado está
     * em binário e não deve ser alterado nunca. O tamanho do template é de 844
     * bytes.
     *
     * @param Inner
     * @param OnLine
     * @param Template
     * @return
     */
    public static native int ReceberUsuarioCadastradoBio(int Inner, int OnLine, byte[] Template);

    /**
     * Solicita a quantidade de usuários cadastrados no Inner Bio.
     *
     * @param Inner
     * @return
     */
    public static native int SolicitarQuantidadeUsuariosBio(int Inner);

    /**
     * Retorna o resultado do comando SolicitarQuantidadeUsuariosBio, a
     * quantidade de usuários cadastrados no Inner Bio é retornado por
     * referência nos parâmetros da função. Inner: 1 a 32 – Para comunicação
     * serial. 1 a 99 – Para comunicação TCP/IP com porta variável. 1 a ... –
     * Para comunicação TCP/IP porta fixa. OnLine: 0 – O comando foi utilizado
     * com o Inner em OffLine. 1 – O comando foi utilizado com o Inner em OnLine
     * Quantidade - Total de usuários cadastrados no Inner Bio.
     *
     * @param Inner
     * @param OnLine
     * @param Quantidade
     * @return
     */
    public static native int ReceberQuantidadeUsuariosBio(int Inner, int OnLine, int[] Quantidade);

    /**
     * Prepara a dll para iniciar a coleta dos usuários do Inner bio, essa
     * função deve ser chamada obrigatoriamente no início do processo.
     */
    public static native void InicializarColetaListaUsuariosBio();

    /**
     * Retorna 1 se ainda existe mais pacotes da lista de usuários, a ser
     * recebido do Inner Bio.
     *
     * @return
     */
    public static native int TemProximoPacote();

    /**
     * Recebe um usuário por vez do pacote recebido anteriormente. O número do
     * usuário é retornado pelo parâmetro da função. Inner: 1 a 32 – Para
     * comunicação serial. 1 a 99 – Para comunicação TCP/IP com porta variável.
     * 1 a ... – Para comunicação TCP/IP porta fixa. Usuario - Número do cartão
     * do usuário cadastrado.
     *
     * @param Inner
     * @param Usuario
     * @return
     */
    public static native int ReceberUsuarioLista(int Inner, StringBuffer Usuario);

    /**
     * Retorna 1 se ainda existe usuários no pacote recebido da lista.
     *
     * @return
     */
    public static native int TemProximoUsuario();

    /**
     * Solicita o pacote(a parte) atual da lista de usuários do Inner bio.
     * Inner: 1 a 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP
     * com porta variável. 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int SolicitarListaUsuariosBio(int Inner);

    /**
     * Solicita o pacote(a parte) atual da lista de usuários do Inner bio.
     * Inner: 1 a 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP
     * com porta variável. 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int SolicitarListaUsuariosBioVariavel(int Inner);

    /**
     * Receber o pacote solicitado pela função SolicitarListaUsuariosBio. Inner:
     * 1 a 32 – Para comunicação serial. 1 a 99 – Para comunicação TCP/IP com
     * porta variável. 1 a ... – Para comunicação TCP/IP porta fixa.
     *
     * @param Inner
     * @return
     */
    public static native int ReceberPacoteListaUsuariosBio(int Inner);

    /**
     * Envia um template com duas digitais para o Inner Bio cadastrar no seu
     * banco de dados. O resultado do cadastro deve ser verificado no retorno da
     * função UsuarioFoiEnviado. Inner: 1 a 32 – Para comunicação serial. 1 a 99
     * – Para comunicação TCP/IP com porta variável. 1 a ... – Para comunicação
     * TCP/IP porta fixa. Template: O cadastro do usuário já contendo as duas
     * digitais e o número do usuário. É um array de bytes com o tamanho de 844
     * bytes.
     *
     * @param Inner
     * @param Template
     * @return
     */
    public static native int EnviarUsuarioBio(int Inner, byte[] Template);

    /**
     * Retorna o resultado do cadastro do Template no Inner Bio, através da
     * função EnviarUsuarioBio. Se o retorno for igual a 0 é porque o template
     * foi cadastrado com sucesso. Inner: 1 a 32 – Para comunicação serial. 1 a
     * 99 – Para comunicação TCP/IP com porta variável. 1 a ... – Para
     * comunicação TCP/IP porta fixa. OnLine: 0 – O comando foi utilizado com o
     * Inner em OffLine. 1 – O comando foi utilizado com o Inner em OnLine.
     *
     * @param Inner
     * @param OnLine
     * @return
     */
    public static native int UsuarioFoiEnviado(int Inner, int OnLine);

    /**
     * Configura as formas de entradas de dados para quando o Inner mudar para o
     * modo Off-Line. Esse comando difere do anterior por permitir a
     * configuração de biometria. Através dessa função o Inner pode ser
     * configurado para trabalhar com verificação ou identificação biométrica,
     * quando ocorrer uma mudança automática de On-Line para Off-Line. Teclado:
     * 0 – Não aceita dados pelo teclado. 1 – Aceita dados pelo teclado.
     * Leitor1: 0 – desativado 3 – entrada e saída 4 – saída e entrada (nesse
     * caso força Leitor2 igual a zero)</param>
     * Leitor2: 0 – desativado 3 – entrada e saída Verificacao: 0 – desativada 1
     * – ativada Identificacao: 0 – desativada 1 – ativada
     *
     * @param Teclado
     * @param Leitor1
     * @param Leitor2
     * @param Verificacao
     * @param Identificacao
     * @return
     */
    public static native int DefinirEntradasMudancaOffLineComBiometria(int Teclado, int Leitor1, int Leitor2, int Verificacao, int Identificacao);

    /**
     * Liga led vermelho InnerAcesso
     *
     * @param Inner
     * @return
     */
    public static native int LigarLedVermelho(int Inner);

    /**
     * Desligar led vermelho InnerAcesso
     *
     * @param Inner
     * @return
     */
    public static native int DesligarLedVermelho(int Inner);

    /**
     * Método que retorna os segundos do Sistema.
     *
     * @return
     */

    public static native int ConfigurarAjustesSensibilidadeBio(int Inner, byte Ganho, byte Brilho, byte Contraste);
   
    public static native int ConfigurarAjustesQualidadeBio(int Inner, byte Registro, byte Verificacao);
    
    public static native int ConfigurarAjustesSegurancaBio(int Inner, byte Identificacao, byte Verificacao);
    
    public static native int ConfigurarCapturaAdaptativaBio(int Inner, byte Capturar, byte Total, byte Tempo);
    
    public static native int ConfigurarFiltroBio(int Inner, byte Habilitar);
    
    public static native int ConfigurarTimeoutIdentificacao(byte TimeoutIdentificacao);

    public static native int ConfigurarNivelLFD(byte NivelLFD);    
    
    public static native int EnviarAjustesBio(int Inner);

    public static native int DefinirMensagemApresentacaoEntrada(int ExibirData, String msg);
    
    public static native int DefinirMensagemApresentacaoSaida(int ExibirData, String msg);

    public static native int InserirHorarioMudancaEntrada(byte Hora1, byte Minuto1, byte Hora2, byte Minuto2, byte Hora3, byte Minuto3);
    
    public static native int InserirHorarioMudancaSaida(byte Hora1, byte Minuto1, byte Hora2, byte Minuto2, byte Hora3, byte Minuto3);
    
    public static native int EnviarBufferEventosMudancaAuto(int Inner);
    
    ///Novos comandos versão 6xx 4.0.0
    public static native int ReceberVersaoFirmware6xx(int Inner, int[] Versao);
    
    public static native int ReceberConfiguracoesInner(int Inner, byte[] ConfiguracoesInner);

    public static native int RequisitarQuantidadeUsuariosBio(int Inner, int TipoComBio);

    public static native int RespostaQuantidadeUsuariosBio(int Inner, int[] QtdUsuariosBio);

    public static native int RequisitarListarUsuariosBio(int Inner, int TipoComBio, int NumPacote);

    public static native int RespostaListarUsuariosBio(int Inner, int[] QtdPacotes, int[] Tamanho);

    public static native int ReceberListaPacUsuariosBio(int Inner, byte[] ListaUsuarios, int TamanhoBuffer);

    public static native int EnviarDigitalUsuarioBio(int Inner, int TipoModBio, String Usuario, byte[] Digital1, byte[] Digital2);

    public static native int RespostaEnviarDigitalUsuarioBio(int Inner);

    public static native int RequisitarModeloBio(int Inner, int TipoComBio);

    public static native int RespostaModeloBio(int Inner, byte[] ModeloBio);

    public static native int RequisitarVersaoBio(int Inner, int TipoComBio);

    public static native int RespostaVersaoBio(int Inner, byte[] VersaoBio);

    public static native int RequisitarExcluirUsuarioBio(int Inner, int TipoComBio, String Usuario);

    public static native int RespostaExcluirUsuarioBio(int Inner);

    public static native int RequisitarExcluirTodosUsuariosBio(int Inner, int TipoComBio);

    public static native int RespostaExcluirTodosUsuariosBio(int Inner);

    public static native int RequisitarUsuarioCadastradoBio(int Inner, int TipoModBio, String Usuario);

    public static native int RespostaUsuarioCadastradoBio(int Inner, int[] TamanhoReceber);

    public static native int ReceberDigitalUsuarioBio(int Inner, byte[] BufferTemplate, int TamanhoReceber);

    public static native int RequisitarEnviarAjustesBio(int Inner, int TipoModBio, byte[] AjustesBio);

    public static native int RespostaEnviarAjustesBio(int Inner);

    public static native int RequisitarVerificarDigitalBio(int Inner, int TipoModBio, String Usuario);

    public static native int RespostaVerificarDigitalBio(int Inner);

    public static native int RequisitarIdentificarUsuarioLeitorBio(int Inner, int TipoModBio);

    public static native int RespostaIdentificarUsuarioLeitorBio(int Inner, byte[] UsuarioIdentificado);

    public static native int RequisitarReceberTemplateLeitorInnerBio(int Inner, int TipoModBio);

    public static native int RespostaReceberTemplateLeitorInnerBio(int Inner, int[] Tamanho);  

    public static native int ReceberTemplateLeitorInnerBio(int Inner, byte[] TemplateRecebido, int Tamanho);

    public static native int RequisitarVerificarCadastroUsuarioBio(int Inner, int TipoModBio, String Usuario);

    public static native int RespostaVerificarCadastroUsuarioBio(int Inner);

    public static native int RequisitarHabilitarIdentificacaoVerificacao(int Inner, int TipoModBio, int Identificacao, int Verificacao);

    public static native int RespostaHabilitarIdentificacaoVerificacao(int Inner);
    
    public static native int LigarLedVerde(int Inner); 
    
    public static native int DesabilitarRele1(int Inner);
    
    public static native int DesabilitarRele2(int Inner);
}
