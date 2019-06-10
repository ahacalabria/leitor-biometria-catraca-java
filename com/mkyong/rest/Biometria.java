/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

import com.nitgen.SDK.BSP.NBioBSPJNI;
import com.nitgen.SDK.BSP.NBioBSPJNI.INPUT_FIR;
//import com.nitgen.SDK.BSP.NBioBSPJNI.PAYLOAD;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ahacalabria
 */
public class Biometria {
    static NBioBSPJNI bsp = new NBioBSPJNI();
    static NBioBSPJNI.Export exportEngine;
    static BiometriaDAO dao = new BiometriaDAO();
    private static Biometria instancia;
    private String[] biometrias;

    public Biometria(){
        bsp = new NBioBSPJNI();
        exportEngine = bsp.new Export();
//        biometrias = dao.readDataBase();
    }
    
    public static synchronized Biometria getInstance(){
        if(instancia==null) instancia = new Biometria();
        return instancia;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        while(true){

        bsp = new NBioBSPJNI();
        exportEngine = bsp.new Export();
        NBioBSPJNI.INPUT_FIR inputFIR1 = getBiometriaDedo();
//        BiometriaDAO dao = new BiometriaDAO();
//        String biometrias[] = new String[1];
//        try {
//            biometrias = dao.readDataBase();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        Biometria b = new Biometria();
//        boolean searchBiometria = b.searchBiometria(biometrias,"000000037589c46a45fdbdf92effce2aa925a31d01d6d8378e7805602355b40d7892c8df3125282a43a044534e883aa9d11cbeee007cfbe9313633ae5443faa0795086369bbf1614e38ad0c9ff20faf8c9aeed3f40241d30f318ecff0b60becffe30364eeec2f96548381f5180c4140187d630ec9ae9dca7d2e97129018c0c779436ff9456a13b9f6beaabaadd581fde6ef48fa41187e81212857ec7a63104550db27ad98c97d2e19d01d5215a7d5e7a85a26f5e6ac3a031515ea8698834d608121487e471d6e25486d0635bf4d5096728393f618727b89fda3f374c28353340f89c53e2aacb3c6ef1d3a468202290eb0c63b339f3a4fc4fa9590336c191845d11812824464acb3ba12b1641730ae5d542a10bc55c6a58ebb2bf6a86243650e520bd2588c7e39e329292fe42c8561c7db00bbe8b6aa132a23d4432bd4a4275395c5cae5a4a9a7c08ed3b14c614e419ce93285143532df03a3a432188707f40471b40c4320bdc0e129875ce0ba627cc770272f6d03c8df12fc17e1a6f60802925583d4c568ce745cc48bb18301b886eaa1c897a71");
//        System.out.println(searchBiometria);
        //        NBioBSPJNI.INPUT_FIR inputFIR2 = getBiometriaDedo();
        //        verificarBio(inputFIR1, inputFIR2);
        //        String biometria_salva = "00000003d9e096597814def1ab2f45fe247be43f7c45b6d4fd686d4c49d7c86b29a28280ff3e44f317ac80e99c7c14421ebc49e7be91e41614df87ad586c88840ff047a6a97090d896252641a1d8bd44e678c4fdadc3375790819b9a632c88cea298bae3daca9c23a62fc0ebf1e6d83203f155aa77d1ed8a7e578570cc1ae0a84bc96b7666e3c5bd6a8eb667f960e8956e477427104f154584b1afdb7835f342655c5fd41ccf2258a6d1c185836b07f7a3f760bc7a082b77df46676274f726bab9685fc965e23e861a53d6f10cb872b6e077a8bb3b816c08490b0db1e07ee94b30557124755c2e773016d2f77503d638bac08f3f07939f7f0a50f3bfccbfae3323fa52987d23970328320302caff79357a04293671296365c2d9d10d5a716bfd72410d79832cf4043b215e28ad9057c8a13d24db32b18dfc02255d45481600bf4d5d1bcb2b2225ba28397bdcd93646cda1726440d1ffd4907d551a883fffe30e83781b6e4a65496131d2d95b7ac5f34707e2dd59d0dc5778a97c5f4f2d50988ab4eef1ce418699b98cd6d6560fad45eb1a4c48fd";
                StringBuilder digitalCapturada = new StringBuilder();
                NBioBSPJNI.Export.DATA exportData = exportEngine.new DATA();;
                exportEngine.ExportFIR(inputFIR1, exportData, NBioBSPJNI.EXPORT_MINCONV_TYPE.FIM01_HV);
                StringBuilder biometria_2 = digitalCapturada.append(convertArrayByteToHex(exportData.FingerData[0].Template[0].Data));
        //        byte[] a = convertHexStringToByteArray(biometria_salva);
        //        byte[] b = convertHexStringToByteArray(biometria_2+"");
        //        System.out.println(biometria_salva);
                System.out.println(biometria_2);
        //verificar2dedos(a,b);
        //            LeitorBiometrico l = new LeitorBiometrico();
        //            String digital = l.registrarDigital();
        //            l.inserirDigital(1,biometria_salva);
        //            String digital2 = l.registrarDigital();
        //            l.verificarDigital(1,digital2);
        //        }
    }
    
public static String verificarBio(INPUT_FIR inputFIR, INPUT_FIR inputFIR2) {

        Boolean bResult = new Boolean(false);
        NBioBSPJNI bsp;
        bsp = new NBioBSPJNI();
        bsp.VerifyMatch(inputFIR, inputFIR2, bResult, null);
        if (bsp.IsErrorOccured() == false) {
            if (bResult) {
                System.out.println("Verify OK - Payload: " + null);
            } else {
                System.out.println("Verify Failed");
            }
        }
        return "";
    }

    public static boolean verificar2dedos(byte[] byTemplate1, byte[] byTemplate2) {
        String msg = "";

//		byte [] a = Base64.decode(Base64_template1,Base64.DEFAULT);
//        byte[] byTemplate1 = a;
//        byte[] byTemplate2 = b;
        
        if (byTemplate1 != null && byTemplate2 != null) {
            NBioBSPJNI.FIR_HANDLE hLoadFIR1, hLoadFIR2;

                hLoadFIR1 = bsp.new FIR_HANDLE();

                exportEngine.ImportFIR(byTemplate1, byTemplate1.length, NBioBSPJNI.EXPORT_MINCONV_TYPE.FIM01_HV, hLoadFIR1);

                if (bsp.IsErrorOccured()) {
                    msg = "Template NBioBSP ImportFIR Error: " + bsp.GetErrorCode();
                    //tvInfo.setText(msg);
                    return false;
                }

                hLoadFIR2 = bsp.new FIR_HANDLE();

                exportEngine.ImportFIR(byTemplate2, byTemplate2.length, NBioBSPJNI.EXPORT_MINCONV_TYPE.FIM01_HV, hLoadFIR2);

                if (bsp.IsErrorOccured()) {
                    hLoadFIR1.dispose();
                    msg = "Template NBioBSP ImportFIR Error: " + bsp.GetErrorCode();
                    //tvInfo.setText(msg);
                    return false;
                }
            
            // Verify Match
            NBioBSPJNI.INPUT_FIR inputFIR1, inputFIR2;
            Boolean bResult = new Boolean(false);

            inputFIR1 = bsp.new INPUT_FIR();
            inputFIR2 = bsp.new INPUT_FIR();

            inputFIR1.SetFIRHandle(hLoadFIR1);
            inputFIR2.SetFIRHandle(hLoadFIR2);

            bsp.VerifyMatch(inputFIR1, inputFIR2, bResult, null);

            if (bsp.IsErrorOccured()) {
                msg = "Template NBioBSP Verify Match Error: " + bsp.GetErrorCode();
                return false;
            } else {
                if (bResult) {
                    msg = "Biometria compatível - SUCESSO";                    
                } else {
                    msg = "Biometria incompatível - FALHOU";
                    hLoadFIR1.dispose();
                    hLoadFIR2.dispose();
                }
                return bResult;
            }
        } else {
            msg = "Can not find captured data";
        }
        System.out.println(msg);
        return false;
    }

    public static NBioBSPJNI.INPUT_FIR getBiometriaDedo() {
        // TODO code application logic here
        NBioBSPJNI nBioBSP = new NBioBSPJNI();
        NBioBSPJNI.FIR_HANDLE hSavedFIR = nBioBSP.new FIR_HANDLE();
        NBioBSPJNI.Export.DATA exportData = exportEngine.new DATA();
        NBioBSPJNI.WINDOW_OPTION winOption = nBioBSP.new WINDOW_OPTION();
        INPUT_FIR inputFIR = nBioBSP.new INPUT_FIR();
        StringBuilder digitalCapturada2 = new StringBuilder();

        try {

            nBioBSP.OpenDevice();
            //nBioBSP.Capture(NBioBSPJNI.FIR_PURPOSE.VERIFY, hSavedFIR, 5000, null, winOption);
            nBioBSP.Capture(hSavedFIR);
            nBioBSP.CloseDevice();
            if (nBioBSP.IsErrorOccured() == false) {
                inputFIR.SetFIRHandle(hSavedFIR);
//                exportEngine.ExportFIR(inputFIR, exportData, NBioBSPJNI.EXPORT_MINCONV_TYPE.FIM01_HV);
//                digitalCapturada2.append(convertArrayByteToHex(exportData.FingerData[0].Template[0].Data));
            }
        } catch (Exception ex) {
            try {
                throw new Exception("Erro ao capturar digital :", ex);
            } catch (Exception ex1) {
                Logger.getLogger(Biometria.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        int erro = nBioBSP.GetErrorCode();
        if (erro == 0) {
            System.out.println(erro);
        }
        System.out.println("Digital capturada dedo: !\n" + digitalCapturada2);
//        return digitalCapturada2
        return inputFIR;

    }
    
    public boolean searchBiometria(String bio_cap){
        System.out.println("String buscada");
        if(bio_cap.equals("")) return false;
        System.out.println("bio_cap:"+bio_cap);
        byte[] a = null;
        byte[] b = null;
        a = convertHexStringToByteArray(bio_cap);
        for (String bio : this.biometrias) {
            b = convertHexStringToByteArray(bio);
//        System.out.println(biometria_salva);
//        System.out.println(biometria_2);
            if(verificar2dedos(a,b)) return true;
        }
        return false;
    }
    
    public String getBiometriaDedoToString() {
        // TODO code application logic here
        NBioBSPJNI nBioBSP = new NBioBSPJNI();
        NBioBSPJNI.FIR_HANDLE hSavedFIR = nBioBSP.new FIR_HANDLE();
        NBioBSPJNI.Export.DATA exportData = exportEngine.new DATA();
        NBioBSPJNI.WINDOW_OPTION winOption = nBioBSP.new WINDOW_OPTION();
        INPUT_FIR inputFIR = nBioBSP.new INPUT_FIR();
        StringBuilder digitalCapturada2 = new StringBuilder();

        try {

            nBioBSP.OpenDevice();
//            nBioBSP.Capture(NBioBSPJNI.FIR_PURPOSE.VERIFY, hSavedFIR, 5000, null, winOption);
            nBioBSP.Capture(hSavedFIR);
            nBioBSP.CloseDevice();
            if (nBioBSP.IsErrorOccured() == false) {
                inputFIR.SetFIRHandle(hSavedFIR);
                exportEngine.ExportFIR(inputFIR, exportData, NBioBSPJNI.EXPORT_MINCONV_TYPE.FIM01_HV);
                digitalCapturada2.append(convertArrayByteToHex(exportData.FingerData[0].Template[0].Data));
            }
        } catch (Exception ex) {
            try {
                throw new Exception("Erro ao capturar digital :", ex);
            } catch (Exception ex1) {
                Logger.getLogger(Biometria.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        int erro = nBioBSP.GetErrorCode();
        if (erro == 0) {
            System.out.println(erro);
        }
//        System.out.println("Digital capturada dedo: !\n" + digitalCapturada2);
//        return digitalCapturada2
        return digitalCapturada2+"";

    }

    public static byte[] convertHexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
    // NOVO
    public static StringBuffer convertArrayByteToHex(byte[] P) {
        int iVlrAsc = 0;
        StringBuffer sSaida = new StringBuffer();
        int i = 0;

        for (i = 0; i <= P.length - 1; i++) {
            iVlrAsc = (P[i] < 0 ? (P[i] + 256) : P[i]);

            sSaida.append(completaString(Integer.toHexString(iVlrAsc), 2, "0"));
        }

        return (sSaida);
    }

    public static String completaString(String var1, int Len, String complemento) {
        while (var1.length() < Len) {
            var1 = complemento + var1;
        }
        return (var1);
    }

}
