package example.proyectofinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PartidaController {




    @FXML
    private GridPane gridPane;
    @FXML
    private Button PRUEBA;
    private Stage scene;
    private int ancho;
    private int largo;

    public void setDimensiones(int ancho,int largo){
        this.ancho = ancho;
        this.largo = largo;
    }

    public void generarTablero(GridPane originalgridPane){
        System.out.println(ancho + "      " + largo);
        gridPane.getChildren().setAll(originalgridPane.getChildren());
    }

    public void colocarUnidades(){
        PRUEBA.setLayoutX(1);
        PRUEBA.setLayoutY(1);
        PRUEBA.setPrefSize(100, 100);

    }
    public void Informacion(){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MenuController.class.getResource("unidades.fxml"));
        try {

            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setTitle("Pantalla Tablero");
            stage.setScene(scene);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Salir() {
        scene.close();
    }
    @FXML
    public void initialize() {
        PRUEBA.setText("personaje");
        colocarUnidades();
    }











    protected class ProgramaTablero {
        private int rondas = 0;

        protected class Tablero {
            private TableroController ancho;
            private TableroController largo;
            private Casilla[][] casillas;
            private int largo1;
            private int ancho1;


            public Tablero() {
                inicializarTablero();
            }

            protected void inicializarTablero() {
                casillas = new Casilla[largo1][ancho1];
                for (int i = 1; i < largo1; i++) {
                    for (int j = 1; j < ancho1; j++) {
                        casillas[i][j] = new Casilla();
                    }
                }
            }

            protected Casilla obtenerCasilla(int fila, int columna) {
                if (fila >= 0 && fila < largo1 && columna >= 0 && columna < ancho1) {
                    return casillas[fila][columna];
                }
                return null;
            }

            protected class Casilla {
                private boolean ocupada;

                protected Casilla() {
                    this.ocupada = false;
                }

                protected boolean isOcupada() {
                    return ocupada;
                }

                protected void setPropiedad(boolean trueofalse) {
                    this.ocupada = trueofalse;
                }
            }
        }

        protected class Matematico extends Unidades {
            protected Matematico() {
                super("Matematico", 90, 21, 2, 3, "Ciencias");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();
                    return true;
                }
                return false;
            }
        }

        protected class Ingeniero extends Unidades {
            protected Ingeniero() {
                super("Ingeniero", 70, 32, 4, 1, "Ciencias");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();
                    return true;
                }
                return false;
            }
        }

        protected class Medico extends Unidades {
            protected Medico() {
                super("Medico", 120, 17, 2, 3, "Ciencias");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();
                    return true;
                }
                return false;
            }
        }

        protected class Arquitecto extends Unidades {
            protected Arquitecto() {
                super("Arquitecto", 80, 27, 3, 2, "Ciencias");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();
                    return true;
                }
                return false;
            }
        }

        protected class Fisico extends Unidades {
            protected Fisico() {
                super("Fisico", 100, 15, 1, 1, "Ciencias");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();
                    return true;
                }
                return false;
            }
        }

        protected class Filologo extends Unidades {
            protected Filologo() {
                super("Filologo", 80, 27, 3, 2, "Letras");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();
                    return true;
                }
                return false;
            }
        }

        protected class Poeta extends Unidades {
            protected Poeta() {
                super("Poeta", 75, 22, 3, 4, "Letras");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();
                    return true;
                }
                return false;
            }
        }

        protected class Historiador extends Unidades {
            protected Historiador() {
                super("Historiador", 120, 17, 2, 2, "Letras");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();
                    return true;
                }
                return false;
            }
        }

        protected class Periodista extends Unidades {
            protected Periodista() {
                super("Periodista", 70, 32, 4, 1, "Letras");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();
                    return true;
                }
                return false;
            }
        }

        protected class Filosofo extends Unidades {
            protected Filosofo() {
                super("Filosofo", 90, 21, 2, 3, "Letras");
            }

            @Override
            protected String getStats() {
                return super.getStats();
            }

            protected boolean isVivo() {
                return super.isVivo();
            }

            protected void setHp(int hp) {
                super.setHp(hp);
            }

            protected void eliminarUnidad() {
                super.eliminarUnidad();
            }

            protected boolean isInRango() {
                return super.isInRango();
            }

            protected boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
                if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                    rondas++;
                    aparicion_personaje();

                    return true;
                }
                return false;
            }
        }

        public int obtenerNumeroDel1Al5() {
            return (int) (Math.random() * 5) + 1;
        }

        public int obtenerNumeroDel1Arondas() {
            return (int) (Math.random() * rondas) + 1;
        }

        protected void aparicion_personaje() {
            int alea = obtenerNumeroDel1Arondas();
            if (alea == rondas) {
                int numero = obtenerNumeroDel1Al5();
                if (numero == 1) {
                    Matematico matematico = new Matematico();
                    Filosofo filosofo = new Filosofo();
                }
                if (numero == 2) {
                    Medico medico = new Medico();
                    Historiador historiador = new Historiador();
                }
                if (numero == 3) {
                    Filologo filologo = new Filologo();
                    Arquitecto arquitecto = new Arquitecto();
                }
                if (numero == 4) {
                    Poeta poeta = new Poeta();
                    Fisico fisico = new Fisico();
                }
                if (numero == 5) {
                    Ingeniero ingeniero = new Ingeniero();
                    Periodista periodista = new Periodista();
                }
            }

        }

        protected void aparicion_personajeInicial() {
            if (0 == rondas) {
                int numero = obtenerNumeroDel1Al5();
                if (numero == 1) {
                    Matematico matematico = new Matematico();
                    Filosofo filosofo = new Filosofo();
                    Medico medico = new Medico();
                    Historiador historiador = new Historiador();
                }
                if (numero == 2) {
                    Medico medico = new Medico();
                    Historiador historiador = new Historiador();
                    Filologo filologo = new Filologo();
                    Arquitecto arquitecto = new Arquitecto();
                }
                if (numero == 3) {
                    Filologo filologo = new Filologo();
                    Arquitecto arquitecto = new Arquitecto();
                    Poeta poeta = new Poeta();
                    Fisico fisico = new Fisico();
                }
                if (numero == 4) {
                    Poeta poeta = new Poeta();
                    Fisico fisico = new Fisico();
                    Ingeniero ingeniero = new Ingeniero();
                    Periodista periodista = new Periodista();
                }
                if (numero == 5) {
                    Ingeniero ingeniero = new Ingeniero();
                    Periodista periodista = new Periodista();
                    Matematico matematico = new Matematico();
                    Filosofo filosofo = new Filosofo();
                }
            }
        }
    }}

