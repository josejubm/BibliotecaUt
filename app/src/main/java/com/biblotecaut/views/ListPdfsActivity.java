package com.biblotecaut.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.biblotecaut.R;
import com.biblotecaut.adapters.AdapterThumbs;
import com.biblotecaut.models.BookPdf;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ListPdfsActivity extends AppCompatActivity {

    private List<BookPdf> listPdfs;
    private RecyclerView rvThumbs;
    private TextInputEditText edtSearch;
    AdapterThumbs adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pdfs);
        setViews();
        setList();
        setAdapter();
        setEdtListeners();
    }

    private void setEdtListeners() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterList(edtSearch.getText().toString());
            }
        });
    }

    private void setViews() {
        rvThumbs = findViewById(R.id.rv_thumbs);
        edtSearch = findViewById(R.id.edt_search);
    }

    private void setAdapter() {
        adapter = new AdapterThumbs(ListPdfsActivity.this, listPdfs);
        rvThumbs.setAdapter(adapter);
        adapter.setOnImageClickListener(pdf -> {
            openPdf(pdf);
        });
    }

    private void openPdf(BookPdf pdf) {
        Intent intent = new Intent(ListPdfsActivity.this,PdfViewerActivity.class);
        intent.putExtra("pdf",pdf);
        startActivity(intent);
    }

    private void filterList( String query) {
        List<BookPdf> newList =  new ArrayList<>();;
        if(query.isEmpty()) {
            newList = listPdfs;
        } else {
            for (BookPdf book : listPdfs) {
                if (book.getName().toLowerCase().contains(query.toLowerCase())
            || book.getIsbn().toLowerCase().contains(query.toLowerCase()) ) {
                    newList.add(book);
                }
            }
        }

        adapter.updateList(newList);
    }
    private void setList() {
        listPdfs = getBookList();
    }


    public static List<BookPdf> getBookList() {
        List<BookPdf> bookList = new ArrayList<>();

        bookList.add(new BookPdf("Algoritmos: Lógica para Desenvolvimento de Programação de Computadores",
                R.raw.algoritmos_logica_para_desenvolvimiento_de_programacao_de_computadores,
                R.drawable.img_algoritmos_logica_para_desenvolvimiento_de_programacao_de_computadores,
                "1234567890123"));

        bookList.add(new BookPdf("Algoritmos: Teoria e Prática",
                R.raw.algoritmos_teoria_e_pratica,
                R.drawable.img_algoritmos_teoria_e_pratica,
                "2345678901234"));

        bookList.add(new BookPdf("Código Limpo",
                R.raw.codigo_limpio,
                R.drawable.img_codigo_limpio,
                "3456789012345"));

        bookList.add(new BookPdf("Como Ser um Programador Melhor",
                        R.raw.como_ser_um_programador_melhor,
                R.drawable.img_como_ser_um_programador_melhor,
                "4567890123456"));

        bookList.add(new BookPdf("Cracking the Coding Interview",
                R.raw.craking_the_coding_interview,
                R.drawable.img_craking_the_coding_interview,
                "5678901234567"));

        bookList.add(new BookPdf("Estruturas de Dados e Seus Algoritmos",
                R.raw.estruturas_de_dados_e_seus_algoritmos,
                R.drawable.img_estruturas_de_dados_e_seus_algoritmos,
                "6789012345678"));

        bookList.add(new BookPdf("O Programador Apaixonado: Construindo uma Carreira Notável em Desenvolvimento de Software",
                R.raw.o_programador_apaixonado_construindo_uma_carreira_notavel_em_desenvolvimento_de_software,
                R.drawable.img_o_programador_apaixonado_construindo_uma_carreira_notavel_em_desenvolvimento_de_software,
                "7890123456789"));

        bookList.add(new BookPdf("O Programador Pragmático",
                R.raw.o_programador_pragmatico,
                R.drawable.img_o_programador_pragmatico,
                "8901234567234"));

        bookList.add(new BookPdf("Fundamentos da programação de computadores_ algoritmos, PASCAL, C_C++ (padrão ANSI) e JAVA",
                R.raw.fundamentos_da_programacao_de_computadores_algoritmos,
                R.drawable.img_fundamentos_da_programacao_de_computadores_algoritmos,
                "8902234567890"));

        bookList.add(new BookPdf("Lógica de Programação",
                R.raw.logica_de_programacao,
                R.drawable.img_logica_de_programacao,
                "3214323567890"));

        bookList.add(new BookPdf("Lógica de Programação Crie seus primeiros programas usando Javascript e HTML",
                R.raw.logica_de_programacao_crie_seus,
                R.drawable.img_logica_de_programacao_crie_seus,
                "2301234567890"));

        bookList.add(new BookPdf("Treinamento em Lógica de Programação",
                R.raw.treinamento_em_logica_de_programa,
                R.drawable.img_treinamento_em_logica_de_programa,
                "5401234567890"));

        bookList.add(new BookPdf("Desenvolvimento Web com HTML, CSS e JavaScript",
                R.raw.desenvolvimento_web_com_html_css_e_javascript,
                R.drawable.img_desenvolvimento_web_com_html_css_e_javascript,
                "3451234567890"));

        bookList.add(new BookPdf("Guia Front-End",
                R.raw.guia_front_end,
                R.drawable.img_guia_front_end,
                "1233234567890"));


        bookList.add(new BookPdf("Apostila-html-css-javascript",
                R.raw.apostila_html_css_javascript,
                R.drawable.img_apostila_html_css_javascript,
                "9876899456780"));

        bookList.add(new BookPdf("Ebook-gratuito-html-premium",
                R.raw.ebook_gratuito_html_premium,
                R.drawable.img_ebook_gratuito_html_premium,
                "8901234567890"));

        bookList.add(new BookPdf("Php-mysql-javascript-html5-all-in-one-for-dummies",
                R.raw.php_mysql_javascript_html5_all_in_one_for_dummies,
                R.drawable.img_php_mysql_javascript_html5_all_in_one_for_dummies,
                "1234131456790"));

        bookList.add(new BookPdf("PDesenvolvimento Web com Javascript",
                R.raw.a,
                R.drawable.img_1,
                "2345222467890"));
        bookList.add(new BookPdf("JavaScript and JQuery_ Interactive Front-End Web Development",
                R.raw.b,
                R.drawable.img_2,
                "6543234356790"));
        bookList.add(new BookPdf("JavaScript_ JavaScript For Beginners - Learn JavaScript Programming with ease in HALF THE TIME - Everything about the Language, Coding, Programming and Web Pages You need to know",
                R.raw.c,
                R.drawable.img_3,
                "8901234567234"));
        bookList.add(new BookPdf("JavaScript_ O guia definitivo",
                R.raw.d,
                R.drawable.img_4,
                "8901234567891"));
        bookList.add(new BookPdf("Meteor_ Criando aplicações web real-time com JavaScript",
                R.raw.e,
                R.drawable.img_5,
                "8901233457890"));
        bookList.add(new BookPdf("Dominando JavaScript Com Jquery",
                R.raw.f,
                R.drawable.img_6,
                "9876543210987"));

        bookList.add(new BookPdf("Learning Web Design _ A Beginner’s Guide to HTML, CSS, JavaScript, and Web Graphics",
                R.raw.learning_web_design_,
                R.drawable.img_learning_web_design_,
                "8765432109876"));
        bookList.add(new BookPdf("Learning PHP, MySQL, JavaScript, CSS & HTML5_ A Step-by-Step Guide to Creating Dynamic Websites",
                R.raw.learning_php_1,
                R.drawable.img_learning_php_1,
                "6543210987654"));
        bookList.add(new BookPdf("Learning PHP, MySQL & JavaScript_ With jQuery, CSS & HTML5",
                R.raw.learning_php_javascript,
                R.drawable.img_learning_php_javascript,
                "5432109876543"));

        bookList.add(new BookPdf("Design-patterns-com-c-aprenda-padroes-de-projeto-com-os-games_compress",
                R.raw.desing_patterns_com_c,
                R.drawable.img_pesing_patterns_c,
                "2223334445556"));
        bookList.add(new BookPdf("apostila-csharp-orientacao-objetos",
                R.raw.apostila_csharp,
                R.drawable.img_apostila_cssharp,
                "9998887776665"));
        bookList.add(new BookPdf("Use a Cabeça - C#",
                R.raw.usa_la_cabeza,
                R.drawable.img_usa_cabeza,
                "5678901234567"));
        bookList.add(new BookPdf("CSharpIniciantes",
                R.raw.csharpiniciantes,
                R.drawable.img_cchar_principiantes,
                "7776665554443"));

        return bookList;
    }
}