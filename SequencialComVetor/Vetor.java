package Listas.SequencialComVetor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class Vetor<T> implements IVetor<T>, Iterable<T> {

    private final int tamVetor; //por padrAo É 10
    private T[] elementos; //como instanciar: (T[]) new  Object[tamVetor]
    private int contElementos; //contador de elementos
    private int vlIncremento; //valor para redimensionar, por padrao o valor eh 10

    //implemente os 3 construtores!

    public Vetor() {
        tamVetor = 10;
        elementos = (T[]) new Object[this.tamVetor];
        contElementos = 0;
        vlIncremento = 10;

    }

    public Vetor(int tamVetor) {
        this.tamVetor = tamVetor;
        elementos = (T[]) new Object[this.tamVetor];
        contElementos = 0;
        vlIncremento = 10;
    }

    public Vetor(int tamVetor, int incremento) {
        this.tamVetor = tamVetor;
        this.vlIncremento = incremento;
        elementos = (T[]) new Object[this.tamVetor];
        contElementos = 0;

    }


    private Iterator<T> myIterator = new Iterator<T>() {

        private int posicao = 0;

        @Override
        public boolean hasNext() {
            if (posicao < contElementos) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                T elemento = elementos[posicao];
                posicao++;
                return elemento;
            }
        }
    };


    @Override
    public void Adicionar(T elemento) {
        this.redimensionar();
        this.elementos[contElementos] = elemento;
        this.contElementos++;

    }

    @Override
    public void Adicionar(int posicao, T elemento) throws IndexOutOfBoundsException {
        this.redimensionar();
        if (posicao > tamVetor || posicao < 0) {
            throw new IndexOutOfBoundsException();
        } else if (posicao == contElementos) {
            Adicionar(elemento);
        } else {
            for (int i = contElementos; i > posicao; i--) {
                elementos[i] = elementos[i - 1];
            }
            elementos[posicao] = elemento;
            contElementos++;

        }
    }

    @Override
    public void AdicionarInicio(T elemento) {
        //Redimensionar

        //Inserir na posição inicial
        this.Adicionar(0, elemento);
        //Mover todos para direita
    }

    public void AdicionarElementosPosRandom(Vetor<T> elementos) {
        int qtdElementos = this.elementos.length + elementos.Tamanho();
        if (qtdElementos >= tamVetor) {
            this.redimensionar();
        }
        if (this.estaVazia()) {
            for (T elemento : elementos) {
                this.Adicionar(elemento);
            }
        } else {
            Random gerador = new Random();
            int[] posicoes = new int[elementos.Tamanho()];
            for (int i = 0; i < posicoes.length; i++) {
                posicoes[i] = gerador.nextInt(this.Tamanho());
                //imprimindo só pra teste
                //System.out.println("posiçao: " + posicoes[i]);
                //FALTA VERIFICAR REPETIÇÕES
            }
            int contPos = 0;
            for (T elemento: elementos) {
                this.Adicionar(posicoes[contPos], elemento);
                contPos++;
            }
        }
    }

    public int PosElemento (T elemento) {
        for (int i = 0; i < Tamanho() ; i++) {
            if ( elemento.equals(elementos[i]) ) {
                return i;
            }
        }
        return -1;
    }

    public void InserirElementoApos(T elementoProcurado,
                                    T elementoParaInserir){

        if (contem(elementoProcurado)) {
            int pos = PosElemento(elementoProcurado)+ 1;
            Adicionar(pos, elementoParaInserir);
        } else {
            System.out.println("Não foi possível inserir o elemento!");
        }

    }

    public void AdicionarElementosPosPares(Vetor<T> elementos){
        if (this.estaVazia()){
            for (T elemento : this.elementos) {
                this.Adicionar(elemento);
            }
        } else {
            int [] pos = new int[this.Tamanho()];
            int tam = 0;

            int par = 0;
            for (int i = 0; i< this.Tamanho(); i = i++) {
                pos[i] = par;
                par = par + 2;
            }

            int cont = 0;
            if (elementos.Tamanho() < this.Tamanho()){
                for (T elemento:elementos) {
                    this.Adicionar(pos[cont],elemento);
                }
            } else {
                //para implementar em casaaaaa
            }

        }
    }


    @Override
    public void Remover(int posicao) throws IndexOutOfBoundsException {
        if (!(estaVazia() || posicao < 0 || posicao >= this.contElementos)) {
            if (posicao == contElementos - 1) {
                this.RemoverFim();
            } else {
                for (int i = posicao; i < this.contElementos - 1; i++) {
                    this.elementos[i] = this.elementos[i + 1];
                }
                //[2,3,4,5,6]
                contElementos--;
            }

        } else {
            throw new IndexOutOfBoundsException("Vetor Vazio ou Posição inválida!");
        }


    }

    @Override
    public void RemoverElemento(T elemento) {
        if (this.contem(elemento)) {
            for (int i = 0; i < this.contElementos; i++) {
                if (elementos[i].equals(elemento)) {
                    //int posicao = i;
                    this.Remover(i);
                    break;
                }
            }

        } else {
            System.out.println("Este elemento não existe na estrutura.");
        }

    }

    @Override
    public void RemoverInicio() {
        this.Remover(0);
    }

    @Override
    public void RemoverFim() {
        if (this.estaVazia()) {
            System.out.println("Vetor vazio");
        } else {
            elementos[contElementos - 1] = null;
            contElementos--;
        }
    }

    @Override
    public int Tamanho() {
        return this.contElementos;
    }

    @Override
    public void Limpar() {
        elementos = (T[]) new Object[this.tamVetor];
        contElementos = 0;

    }

    @Override
    public boolean contem(T elemento) {
        for (int i = 0; i < this.contElementos; i++) {
            if (elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public boolean estaVazia() {
        if (contElementos == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void redimensionar() {
        if (this.contElementos == this.tamVetor) {
            T[] novoElementos = (T[]) new Object[this.tamVetor + this.vlIncremento];
            for (int i = 0; i < novoElementos.length; i++) {
                novoElementos[i] = elementos[i];
            }
            this.elementos = novoElementos;
        }
    }

    public void redimensionar2() {
        if (this.contElementos == this.tamVetor) {
            T[] novoElementos = (T[]) new Object[this.tamVetor + this.vlIncremento];
            System.arraycopy(this.elementos, 0, novoElementos, 0, novoElementos.length);
            this.elementos = novoElementos;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return myIterator;
    }


}