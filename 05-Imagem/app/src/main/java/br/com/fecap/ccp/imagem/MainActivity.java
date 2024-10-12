package br.com.fecap.ccp.imagem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean pontuacaoFinal = false;  // Controla a contagem de pontos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }





    public void selectPapel(View view){
        this.opcaoSelecionada("papel");
    }
    public void selectPedra(View view){
        this.opcaoSelecionada("pedra");
    }
    public void selectTesoura(View view){
        this.opcaoSelecionada("tesoura");
    }
    public void opcaoSelecionada(String opcaoSelecionada){
        // Instanciamento dos Objetos ImagemView e TextView
        ImageView imagemResultado = findViewById(R.id.imagePadrao);
        TextView textResultado = findViewById(R.id.textResultado);
        TextView textResultApp = findViewById(R.id.textResultApp);
        TextView textResultSelecao = findViewById(R.id.textResultSelecao);
        TextView textPontuacao = findViewById(R.id.textPontuacao);



        // extrai dos Objetos, recuperando a String que pompões:
        String resultApp = textResultApp.getText().toString();
        String resultSelecao = textResultSelecao.getText().toString();
        String Pontuacao = textPontuacao.getText().toString();

        // Converter String para Numérico:
        int resApp = Integer.parseInt(resultApp);
        int resSelecao = Integer.parseInt(resultSelecao);
        int resPontuacao = Integer.parseInt(Pontuacao);


        // Logica de escolha das Opção da Máquina (App):
        int numero = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[numero];

        // Apresentando resultado (imagem) escolhido pela máquina (App):
        switch (opcaoApp){
            case "pedra":
                imagemResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imagemResultado.setImageResource(R.drawable.tesoura);
                break;
        }










        // Logica do Jogo J Ken P - Definir quem é o vencedor
        if (
                (opcaoApp.equals("tesoura") && opcaoSelecionada.equals("papel")) ||
                (opcaoApp.equals("papel") && opcaoSelecionada.equals("pedra")) ||
                (opcaoApp.equals("pedra") && opcaoSelecionada.equals("tesoura"))
        ){
            // Mensagem para o jogador que perdeu
            textResultado.setText(R.string.appJogoGameOver);

            // Soma 1 na variável se os pontos não estiverem finalizados - Melhor de 3
            if (!pontuacaoFinal && resApp < 3) {
                resApp += 1;
                textResultApp.setText(String.valueOf(resApp));
            }
            // Zera a pontuação quando jogador perde
            resPontuacao =0;
            textPontuacao.setText(String.valueOf(resPontuacao));

        } else if (
                (opcaoSelecionada.equals("tesoura") && opcaoApp.equals("papel")) ||
                (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra")) ||
                (opcaoSelecionada.equals("pedra") && opcaoApp.equals("tesoura"))
        ){
            textResultado.setText(R.string.appJogoWin);
            // Soma 1 na variável se os pontos não estiverem finalizados - Melhor de 3
            if (!pontuacaoFinal && resSelecao < 3) {
                resSelecao += 1;
                textResultSelecao.setText(String.valueOf(resSelecao));
            }
            // Soma na Pontuação quando ganha se consecutivo mantêm a contagem
            resPontuacao +=1;
            textPontuacao.setText(String.valueOf(resPontuacao));

        } else {
            textResultado.setText(R.string.appJogoEmpate);
        }
        // Verifica melhor de 3 e finaliza a contagem de pontos
        if (resApp >= 3 || resSelecao >= 3) {
            pontuacaoFinal = true;
            if (resApp >= 3) {
                textResultApp.setTextColor(getResources().getColor(R.color.appFonte2));

            } else {
                textResultSelecao.setTextColor(getResources().getColor(R.color.appFonte2));
            }
        }
    }



    public void reiniciar(View view){
        // Instanciamento dos Objetos ImagemView e TextView
        ImageView imagemResultado = findViewById(R.id.imagePadrao);
        TextView textResultApp = findViewById(R.id.textResultApp);
        TextView textResultado = findViewById(R.id.textResultado);
        TextView textResultSelecao = findViewById(R.id.textResultSelecao);
        TextView textPontuacao = findViewById(R.id.textPontuacao);
       TextView appName = findViewById(R.id.textApp);
        TextView nameVoce = findViewById(R.id.textVoce);
        TextView textMelhorDe3 = findViewById(R.id.textNameMelhorDe3);
        TextView textX = findViewById(R.id.textSimboloX);
        TextView textNamePont = findViewById(R.id.textNamePontuacao);

        // Variáveis recebem dados para zerados
        imagemResultado.setImageResource(R.drawable.padrao);
        textResultado.setText(R.string.appTextoResultado);
        textResultApp.setText("0");
        textResultSelecao.setText("0");
        textPontuacao.setText("0");

        //exibir todos os modos de jogo
        textResultSelecao.setTextColor(getResources().getColor(R.color.appFonte1));
        textResultApp.setTextColor(getResources().getColor(R.color.appFonte1));
        textPontuacao.setVisibility(View.VISIBLE);
        textResultApp.setVisibility(View.VISIBLE);
        textResultSelecao.setVisibility(View.VISIBLE);
        appName.setVisibility(View.VISIBLE);
        nameVoce.setVisibility(View.VISIBLE);
        textMelhorDe3.setVisibility(View.VISIBLE);
        textX.setVisibility(View.VISIBLE);
        textNamePont.setVisibility(View.VISIBLE);
        textPontuacao.setVisibility(View.VISIBLE);
        textResultApp.setVisibility(View.VISIBLE);


        // Reativa a contagem de pontos
        pontuacaoFinal = false;

    }






    // logica para definir o modo do jogo JOKENPO

    public void modoJogo(View view) {

        // instanciando os objetos TextView
        TextView textResultado = findViewById(R.id.textResultado);
        TextView textPontuacao = findViewById(R.id.textPontuacao); // exibir pontuação
        TextView textResultApp = findViewById(R.id.textResultApp); //  exibir resultado do app
        TextView textResultSelecao = findViewById(R.id.textResultSelecao);
        TextView textNamePont = findViewById(R.id.textNamePontuacao);
        TextView appName = findViewById(R.id.textApp);
        TextView nameVoce = findViewById(R.id.textVoce);
        TextView textMelhorDe3 = findViewById(R.id.textNameMelhorDe3);
        TextView textX = findViewById(R.id.textSimboloX);


        // reiniciando os valores
        textResultApp.setText("0");
        textResultSelecao.setText("0");
        textPontuacao.setText("0");
        textResultado.setText(R.string.appTextoResultado);
        textResultSelecao.setTextColor(getResources().getColor(R.color.appFonte1));
        textResultApp.setTextColor(getResources().getColor(R.color.appFonte1));

        // Reativa a contagem de pontos
        pontuacaoFinal = false;



        // ver qual botao foi escolhido.
        if (view.getId() == R.id.btnMelhorDe3) {

        // adicona o modo melhor de tres na tela e esconde a pontuação corrida
            textPontuacao.setVisibility(View.GONE);
           textNamePont.setVisibility(View.GONE);
            textResultApp.setVisibility(View.VISIBLE);
            textPontuacao.setVisibility(View.GONE);
            textResultApp.setVisibility(View.VISIBLE);
            textResultSelecao.setVisibility(View.VISIBLE);
            appName.setVisibility(View.VISIBLE);
            nameVoce.setVisibility(View.VISIBLE);
            textMelhorDe3.setVisibility(View.VISIBLE);
            textX.setVisibility(View.VISIBLE);

        // Se o botão Pontuação Corrida for clicado
        } else if (view.getId() == R.id.btnPontCorrida) {

            // adicionando o modo de pontuação corrida e escondendo o de melhor de 3
            textNamePont.setVisibility(View.VISIBLE);
            textPontuacao.setVisibility(View.VISIBLE);
            textResultApp.setVisibility(View.GONE);
            textResultSelecao.setVisibility(View.GONE);
            appName.setVisibility(View.GONE);
            nameVoce.setVisibility(View.GONE);
            textMelhorDe3.setVisibility(View.GONE);
            textX.setVisibility(View.GONE);

        }
    }








}



