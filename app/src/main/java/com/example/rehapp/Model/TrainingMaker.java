package com.example.rehapp.Model;

import com.example.rehapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TrainingMaker {

    private List<Exercize> generatedExercizeList;
    private static TrainingMaker singleInstance;
    private List<Exercize> exercizeListHard =new ArrayList<>(); //>= 6.5
    private List<Exercize> exercizeListMedium =new ArrayList<>(); //>5 & <6.5
    private List<Exercize> exercizeListEasy =new ArrayList<>(); //<=5

    public static TrainingMaker getInstance() {
        if (singleInstance == null) {
            singleInstance = new TrainingMaker();
            singleInstance.createList();
        }
        return singleInstance;
    }

    /*private Map<String,ArrayList<Exercize>> excercizesHard= new HashMap<>();
    private Map<String,ArrayList<Exercize>> excercizesMedium= new HashMap<>();
    private Map<String,ArrayList<Exercize>> excercizesEasy= new HashMap<>();

    private List<Exercize> exercizeListBalanceHard =new ArrayList<>();
    private List<Exercize> exercizeListBalanceMedium =new ArrayList<>();
    private List<Exercize> exercizeListBalanceEasy =new ArrayList<>();
    private List<Exercize> exercizeListCoreHard =new ArrayList<>();
    private List<Exercize> exercizeListCoreMedium =new ArrayList<>();
    private List<Exercize> exercizeListCoreEasy =new ArrayList<>();
    private List<Exercize> exercizeListSupHard =new ArrayList<>();
    private List<Exercize> exercizeListSupMedium =new ArrayList<>();
    private List<Exercize> exercizeListSupEasy =new ArrayList<>();
    private List<Exercize> exercizeListInfHard =new ArrayList<>();
    private List<Exercize> exercizeListInfMedium =new ArrayList<>();
    private List<Exercize> exercizeListInfEasy =new ArrayList<>();
    private List<Exercize> exercizeListStretchHard =new ArrayList<>();
    private List<Exercize> exercizeListStretchMedium =new ArrayList<>();
    private List<Exercize> exercizeListStretchEasy =new ArrayList<>();
    private List<Exercize> exercizeListRespiroHard =new ArrayList<>();
    private List<Exercize> exercizeListRespiroMedium =new ArrayList<>();
    private List<Exercize> exercizeListRespiroEasy =new ArrayList<>();
    private List<Exercize> exercizeListToraceHard =new ArrayList<>();
    private List<Exercize> exercizeListToraceMedium =new ArrayList<>();
    private List<Exercize> exercizeListToraceEasy =new ArrayList<>();*/

    public List<Exercize> getNewExercizeList(String cat, int edss){
        List<Exercize> res=new ArrayList<>();
        if(edss>=6.5){
            if(cat.equals("E")){
                res.add(new Exercize("Riscaldamento", "Eseguire un'attività di riscaldamento a bassa intensità", R.drawable.ic_warmup_foreground));
                res.add(new Exercize("Attività ad alta intensità", "Eseguire l'attività scelta in sei rieptizioni. Ciascuna ripetizione prevede 3 minuti di attività seguiti da unoo due minuti di riposo", R.drawable.ic_exer_foreground));
                res.add(new Exercize("Defaticamento", "Eseguire un'attività a bassa intensità per rilassare i muscoli", R.drawable.ic_warmup_foreground));
                res.add(new Exercize("Stretching", "Eseguire l'allungamento di tutti i muscoli coinvoliti durante l'allenamento", R.drawable.ic_stretch_foreground));
            }else{
                res.add(new Exercize("Riscaldamento", "Eseguire un'attività di riscaldamento a bassa intensità", R.drawable.ic_warmup_foreground));
                res.addAll(pickNRandom(exercizeListHard, 4)); //da generare random
                res.add(new Exercize("Stretching", "Eseguire l'allungamento di tutti i muscoli coinvoliti durante l'allenamento", R.drawable.ic_stretch_foreground));
            }
        }else if(edss<=5){
            if(cat.equals("E")){
                res.add(new Exercize("Riscaldamento", "Eseguire un'attività di riscaldamento a bassa intensità", R.drawable.ic_warmup_foreground));
                res.add(new Exercize("Attività ad alta intensità", "Eseguire l'attività scelta per almeno 20 minuti ad alta intensità", R.drawable.ic_exercize_foreground));
                res.add(new Exercize("Defaticamento", "Eseguire un'attività a bassa intensità per rilassare i muscoli", R.drawable.ic_warmup_foreground));
                res.add(new Exercize("Stretching", "Eseguire l'allungamento di tutti i muscoli coinvoliti durante l'allenamento", R.drawable.ic_stretch_foreground));
            }else{
                res.add(new Exercize("Riscaldamento", "Eseguire un'attività di riscaldamento a bassa intensità", R.drawable.ic_warmup_foreground));
                res.addAll(pickNRandom(exercizeListEasy, 6)); //da generare random
                res.add(new Exercize("Stretching", "Eseguire l'allungamento di tutti i muscoli coinvoliti durante l'allenamento", R.drawable.ic_stretch_foreground));
            }
        }else{
            if(cat.equals("E")){
                res.add(new Exercize("Riscaldamento", "Eseguire un'attività di riscaldamento a bassa intensità", R.drawable.ic_warmup_foreground));
                res.add(new Exercize("Attività ad alta intensità", "Eseguire l'attività scelta per almeno 15-20 minuti ad alta intensità", R.drawable.ic_exercize_foreground));
                res.add(new Exercize("Defaticamento", "Eseguire un'attività a bassa intensità per rilassare i muscoli", R.drawable.ic_warmup_foreground));
                res.add(new Exercize("Stretching", "Eseguire l'allungamento di tutti i muscoli coinvoliti durante l'allenamento", R.drawable.ic_stretch_foreground));
            }else{
                res.add(new Exercize("Riscaldamento", "Eseguire un'attività di riscaldamento a bassa intensità", R.drawable.ic_warmup_foreground));
                res.addAll(pickNRandom(exercizeListMedium, 5)); //da generare random
                res.add(new Exercize("Stretching", "Eseguire l'allungamento di tutti i muscoli coinvoliti durante l'allenamento", R.drawable.ic_stretch_foreground));
            }
        }
        generatedExercizeList =res;
        return res;
    }

    public List<Exercize> getGeneratedExercizeList(){
        return generatedExercizeList;
    }

    public static List<Exercize> pickNRandom(List<Exercize> lst, int n) {
        List<Exercize> copy = new LinkedList<>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

    public static class Exercize {
        private String title;
        private String description;
        private int imageId;
        private List<String> funcional=new ArrayList<>();

        public Exercize(String title, String description, int imageId, String ... functional){
            this.description=description;
            this.title=title;
            this.imageId = imageId;
            this.funcional= Arrays.asList(functional);
        }

        public String getTitle() { return title; }

        public String getDescription() { return description; }

        public int getImageId() { return imageId; }

        public List<String> getFuncional(){ return funcional; }
    }


    private void createList(){

        // EDSS > 6.5

        exercizeListHard.add(new Exercize("Bicipiti", "Seduto o in piedi. Prendi in mano un peso (esempio: bottiglia d’acqua piena) e posiziona le braccia lungo i fianchi. Porta la mano verso la spalla poi torna giù lentamente. Consiglio: tieni fermo il braccio dal gomito in su. Avvicina l’avambraccio al bicipite.", R.drawable.act002, "AertiSuperiori"));
        exercizeListHard.add(new Exercize("Quadricipiti", "Seduto con i piedi poggiati al suolo. Mantieni la schiena dritta. Solleva verso l’alto il piede mantenendo la coscia ferma. Alterna destro e sinistro.", R.drawable.act036, "ArtiInferiori"));
        exercizeListHard.add(new Exercize("Abduzione dell'anca", "In piedi, mantieniti ad un appoggio. Solleva lateralmente la gamba mantenendo il tronco dritto. Supino: stenditi su un tappetino.Muovi la gamba di lato.Posizione laterale: stenditi su un fianco. Solleva la gamba del fianco che sta in alto, poi torna giù lentamente. Seduto: siediti, apri e chiudi le ginocchia. Potenziamento: lega le gambe con una benda elastica.", R.drawable.act022, "ArtiInferiori"));
        exercizeListHard.add(new Exercize("Squat", "In piedi sostenendoti a un tavolo, piegati su le gambe tendendo la schiena diritta. Solleva le braccia durante la discesa poi abbassale mentre risali.", R.drawable.act026, "Balance", "ArtiInferiori"));
        exercizeListHard.add(new Exercize("Polpacci","Seduto, posiziona un peso sopra le ginocchia (esempio una bottiglia d’acqua piena). Solleva i talloni spingendo sulle punte.", R.drawable.act005, "ArtiInferiori"));  // todo immagine non appropriata
        exercizeListHard.add(new Exercize("Flessione dell'anca da seduto", "Siediti, solleva il ginocchio dalla sedia. Alterna il destro e il sinistro.", R.drawable.act013, "ArtiInferiori"));
        exercizeListHard.add(new Exercize("Flessione/estensione di braccia alternata", "Seduto o in piedi. Prendi in mano un peso (esempio: bottiglia d’acqua piena); fletti il braccio destro e, contemporaneamente, estendi il sinistro e viceversa. Il ritmo deve essere sostenuto.", R.drawable.ic_warmup_foreground, "ArtiSuperiori")); //todo
        exercizeListHard.add(new Exercize("Alzata di spalle", "In piedi o seduti. Aavvicina le spalle alle orecchie, mantieni la posizione per tre secondi poi torna giù lentamente. Incremento: esegui l’esercizio mantenendo dei pesi in mano.", R.drawable.act040, "ArtiSuperiori"));
        exercizeListHard.add(new Exercize("Roll down", "Siediti su di una stuoia con la schiena dritta e le ginocchia flesse. Srotola la schiena verso il suolo, scendendo lentamente. Mantieni le braccia tese avanti. Consiglio: emetti un lungo respiro durante l’esercizio.", R.drawable.act041, "Balance", "Core"));
        exercizeListHard.add(new Exercize("Flessioni laterali del tronco", "Siediti, poi porta le mani dietro la nuca. Avvicina i gomiti al tronco prima da un lato poi d’altro.", R.drawable.act042, "Core"));
        exercizeListHard.add(new Exercize("Cerchi con le braccia", "In piedi o da seduti. Allarga le braccia di lato fino all’altezza delle spalle poi compi con esse dei piccoli cerchi per 30-40 secondi.", R.drawable.act043, "ArtiSuperiori"));
        exercizeListHard.add(new Exercize("Flessione del braccio in diagonale", "In piedi con gambe leggermente divaricate ed un peso in mano. Disegna col braccio una diagonale che va verso l’alto e di lato partendo dal centro del corpo. Con due pesi puoi alternare destro e sinistro in una serie. NB: braccio sempre teso.", R.drawable.act044, "ArtiSuperiori"));
        exercizeListHard.add(new Exercize("Croce", "In piedi con gambe leggermente divaricate, braccia lungo in fianchi con due pesi in mano (bottiglie di plastica piene). Allarga le braccia di lato fino a 90°, muovile in avanti poi torna giù in posizione di partenza. Riparti eseguendo il movimento al contrario.", R.drawable.act045, "ArtiSuperiori"));
        exercizeListHard.add(new Exercize("Spine twist", "Siediti su un tappeto con le gambe poggiate al suolo e la schiena tesa verso l’alto. allarga le braccia poi avvita la colonna in senso orario poi in senso antiorario. NB: mantieni le braccia larghe.", R.drawable.act046, "Core"));
        exercizeListHard.add(new Exercize("Angel wings", "Poggia schiena contro una parete, allarga le braccia di lato con gomiti ad angolo retto poi poggia il dorso alla parete. Fai scivolare il dorso delle mani verso l’alto poi verso il basso lentamente. Puoi eseguirlo anche disteso su un tappetino.", R.drawable.act047, "ArtiSuperiori"));
        exercizeListHard.add(new Exercize("Contrazione isometrica del quadricipite", "Siediti su un tappetino con la schiena poggiata contro una parete e le gambe distese. Arrotola un piccolo asciugamano e posizionalo sotto il ginocchio. Schiaccia con il dorso del ginocchio l’asciugamano per cinque secondi senza staccare il tallone dal tappetino.", R.drawable.act049, "ArtiInferiori"));
        exercizeListHard.add(new Exercize("Respirazione in 4 tempi","Siediti e mantieni la schiena dritta. Prendi aria lentamente per almeno 5 secondi poi mantieni il respiro per 5 secondi, butta fuori l’aria lentamente per almeno 5 secondi poi attendi altri 5 secondi prima di ricominciare.", R.drawable.ic_warmup_foreground, "Respirazione"));
        /*  exercizeListUpTo6.add(new Exercize("Respirazione diaframmatica", "", ""));
            exercizeListUpTo6.add(new Exercize("Swan dive", "", ""));
            exercizeListUpTo6.add(new Exercize("Elevazione spalla", "", ""));
            exercizeListUpTo6.add(new Exercize("Esercizi calistenici da seduto", "", ""));
        */

        // EDSS >5.5 <6.5
        exercizeListMedium.add(new Exercize("Flessione/estensione di braccia alternata", "Seduto o in piedi. Prendi in mano un peso (esempio: bottiglia d’acqua piena); fletti il braccio destro e, contemporaneamente, estendi il sinistro e viceversa. Il ritmo deve essere sostenuto.", R.drawable.ic_warmup_foreground, "ArtiSuperiori")); //todo no immagine
        exercizeListMedium.add(new Exercize("Bicipiti", "Seduto o in piedi. Prendi in mano un peso (esempio: bottiglia d’acqua piena) e posiziona le braccia lungo i fianchi. Porta la mano verso la spalla poi torna giù lentamente. Consiglio: tieni fermo il braccio dal gomito in su. Avvicina l’avambraccio al bicipite.", R.drawable.act002, "AertiSuperiori"));
        exercizeListMedium.add(new Exercize("Ponte","Distenditi su una stuoia con le ginocchia piegate e i piedi ben poggiati. Solleva il bacino verso l’alto spingendo sui talloni poi torna giù lentamente. Contrai i glutei.", R.drawable.act004, "Balance", "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Polpacci","In piedi con appoggia frontale. Solleva i talloni poi torna giù lentamente. Potenziamento: zaino con pesi", R.drawable.act005, "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Alzata dalla sedia","Siediti con le mani incrociate al petto. Alzati dalla sedia poi siediti lentamente.", R.drawable.act030, "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Addominali inversi","Distenditi su una stuoia poggiando bene la pianta dei piedi. Solleva gli arti inferiori verso l’alto poi torna giù lentamente.", R.drawable.act007, "Core"));
        exercizeListMedium.add(new Exercize("Rotazione del bacino","Distenditi su un tappetino con le ginocchia flesse e piedi poggiati al suolo. Oscilla lentamente di lato con le ginocchia aumentando man mano l’arco di movimento. Potenziamento: eseguilo con anche e ginocchia flesse a 90° e piedi sollevati.", R.drawable.act017, "Core"));
        exercizeListMedium.add(new Exercize("Piegamenti su panca","Poggia una sedia contro il muro poi posiziona le mani agli angoli della sedia e fai un ampio passo indietro avvicinando il petto alla sedia. Tieni la schiena dritta. Spingi forte sulle mani sollevando il petto dalla sedia poi torna giù lentamente.", R.drawable.act020, "ArtiSuperiori"));
        exercizeListMedium.add(new Exercize("Piegamenti con ginocchia poggiate","Posizionati disteso a pancia in giù con le mani all’altezza delle spalle e appoggia le ginocchia per terra. Contrai l’addome. Spingi forte sulle mani sollevando il petto dal suolo poi torna giù lentamente.", R.drawable.act021, "ArtiSuperiori"));
        exercizeListMedium.add(new Exercize("Abduzione dell'anca", "In piedi, mantieniti ad un appoggio. Solleva lateralmente la gamba mantenendo il tronco dritto. Supino: stenditi su un tappetino.Muovi la gamba di lato.Posizione laterale: stenditi su un fianco. Solleva la gamba del fianco che sta in alto, poi torna giù lentamente. Seduto: siediti, apri e chiudi le ginocchia. Potenziamento: lega le gambe con una benda elastica.", R.drawable.act022, "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Flessione dell'anca","Posizione supina: distenditi completamente su un tappetino. Solleva una gamba mantenendola tesa poi torna giù lentamente. Alterna destra e sinistra. In piedi: in piedi con appoggio frontale, solleva il ginocchio fino all’altezza del bacino poi torna giù lentamente. Alterna destra e sinistra.", R.drawable.act013, "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Addominali obliqui","Stenditi su di un fianco con la mano con la mano del fianco opposto che tocca la nuca. Avvicina il gomito del fianco libero al bacino poi ritorna lentamente.", R.drawable.act034, "Core"));
        exercizeListMedium.add(new Exercize("Anti-retro versione del bacino, supino","Stenditi su un tappeto con le gambe flesse. Inarca il tratto lombare staccandolo dal suolo poi torna giù lentamente.", R.drawable.act037, "Core"));
        exercizeListMedium.add(new Exercize("Anti-retro versione del bacino, seduto","Siediti mantenendo la schiena dritta. Inarca il tratto lombare in avanti staccandolo dallo schienale poi ritorna lentamente. NB: spalle e testa ferme: si muove solo la lombare.", R.drawable.act037, "Core"));
        exercizeListMedium.add(new Exercize("Anti-retro versione del bacino, carponi","Posizionati a carponi con le mani all’altezza delle spalle e ginocchia all’altezza del bacino. Inarca verso il basso il tratto lombare poi spingilo verso l’alto (esercizio del cane/gatto).", R.drawable.act037, "Core"));
        exercizeListMedium.add(new Exercize("Roll down", "Siediti su di una stuoia con la schiena dritta e le ginocchia flesse. Srotola la schiena verso il suolo, scendendo lentamente. Mantieni le braccia tese avanti. Consiglio: emetti un lungo respiro durante l’esercizio.", R.drawable.act041, "Balance", "Core"));
        exercizeListMedium.add(new Exercize("Slanci laterali a carponi","A carponi con le mani all’altezza delle spalle, ginocchia all’altezza delle anche. Contrai l’addome. Solleva lateralmente la gamba poi torna giù lentamente. Alterna destra e sinistra.", R.drawable.act016, "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Sitting forward band","Siediti su un tappetino con le gambe stese. Chinati sulle gambe lentamente portando le mani verso dei piedi. NB: durante il movimento espira lentamente.", R.drawable.act025, "Stretching"));
        exercizeListMedium.add(new Exercize("Tacco punta","In piedi, solleva i talloni, torna giù poi solleva le punte dei piedi.", R.drawable.act028, "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Step up","Avvicinati ad un gradino. Sali il gradino con un piede poi fai seguire l’altro. Alterna destro e sinistro in salita. Evoluzione: Sali il gradino con piede, poi solleva il ginocchio controlaterale. Potenziamento: procurati uno step più alto.", R.drawable.act027, "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Tricipiti","Poggia una sedia contro il muro o avvicinati ad una superficie stabile non più alta del tuo bacino. Posizionati di spalle alla superficie poi poggia le mani sul bordo. Mettiti con le braccia verso il suolo poi spingi sulle mani per tornare su.", R.drawable.act003, "ArtiSuperiori"));
        exercizeListMedium.add(new Exercize("Piegamenti a muro","Posizionati di fronte ad una parete poi poggia le mani poco più in basso delle spalle con le braccia tese. Porta i gomiti dietro avvicinando il petto al muro poi spingi per allontanarti. Incremento: aumenta la distanza dal muro. NB: bacino-dorso-capo sempre allineati. Non allargare i gomiti dal tronco.", R.drawable.act038, "ArtiSuperori", "Torace"));
        exercizeListMedium.add(new Exercize("Squat isometrico a muro","Poggia la schiena ad una parete e posiziona i piedi avanti. Scendi senza staccare la schiena dalla parete poi spingi per risalire. Non scendere oltre il livello delle ginocchia.", R.drawable.act039, "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Spostamento laterale del peso","In piedi. Sul posto, oscilla lentamente di lato con il corpo. Oppure sposta il peso a destra e sinistra senza perdere l’equilibrio. NB: non slegare il bacino dal tronco.", R.drawable.ic_warmup_foreground, "Balance"));  //todo no immagine
        exercizeListMedium.add(new Exercize("Tandem stance","Avvicinati a una parete o a un appoggio. Posiziona un piede di fronte l’altro poi rimani in equilibrio per 30 secondi. Evoluzione: chiudi gli occhi. NB: allargare le braccia aiuta a trovare l’equilibrio, viceversa incrociare le mani al petto aumenta la difficoltà.", R.drawable.act031, "Balance"));
        exercizeListMedium.add(new Exercize("Passo laterale","In piedi: cammina di lato come se dovessi attraversare un corridoio molto stretto.", R.drawable.act032, "ArtiInferiori"));
        exercizeListMedium.add(new Exercize("Espirazione contro resistenza","Procurati una bottiglietta di plastica e una cannuccia poi riempila a metà con acqua. Soffia con vigore e costanza nella cannuccia per almeno 10 secondi. Riposa 30 secondi prima di ricominciare.", R.drawable.ic_warmup_foreground, "Respirazione")); //todo no immagine
        exercizeListMedium.add(new Exercize("Respirazione in 4 tempi","Siediti e mantieni la schiena dritta. Prendi aria lentamente per almeno 5 secondi poi mantieni il respiro per 5 secondi, butta fuori l’aria lentamente per almeno 5 secondi poi attendi altri 5 secondi prima di ricominciare.", R.drawable.ic_warmup_foreground, "Respirazione")); //todo no immagine
        exercizeListMedium.add(new Exercize("Twist","Stenditi su un tappetino con le ginocchia piegate e le dita delle mani incrociate. Mantieni l’addome contratto. Esecuzione: solleva schiena e gambe dal tappeto poi ruota con le spalle da un lato all’altro toccando ogni volta il tappetino con le mani. Incremento: mantieni in mano un peso (bottiglia di plastica piena)", R.drawable.ic_warmup_foreground, "Balance", "Core")); //todo fare immagine da file
        exercizeListMedium.add(new Exercize("Windmill arms","Stenditi su un tappetino con le ginocchia piegate, i piedi ben poggiati, le braccia lungo i fianchi e i palmi che toccano la superficie. 1 step: solleva le braccia fino a 90°. 2 step: il braccio destro continua il movimento di elevazione oltre il capo mentre il braccio sinistro torna giù. 3 step: muovi le braccia di lato con senso di rotazione opposto. 4 step: riporta le braccia in alto ad angolo retto.", R.drawable.ic_warmup_foreground, "ArtiSuperiori"));
        /*  exercizeListMedium.add(new Exercize("Semi-affondi", "", ""));
            exercizeListMedium.add(new Exercize("Elevazione spalla","", ""));
            exercizeListMedium.add(new Exercize("Alzate posteriori a carponi","", ""));
            exercizeListMedium.add(new Exercize("Scapular adduction","", ""));
            exercizeListMedium.add(new Exercize("Shoulder abduction unilaterale","", ""));
            exercizeListMedium.add(new Exercize("Single leg stance","", ""));
            exercizeListMedium.add(new Exercize("Cammino sulle punte / cammino sui talloni","", ""));
            exercizeListMedium.add(new Exercize("Esercizi calistenici da seduto","", ""));
            exercizeListMedium.add(new Exercize("Respirazioni profonde","Prendi un lungo e profondo respiro gonfiando al massimo il petto (min 5 sec). Butta fuori l’aria con le labbra socchiuse gonfiando le guance. NB: nell’espirazione si deve avvertire una resistenza alla fuoriuscita dell’aria", R.drawable.ic_warmup_foreground)); //todo no immaine
        */

        // EDSS < 5.5
        exercizeListEasy.add(new Exercize("Affondi","In piedi con le gambe leggermente divaricate. Fai un passo in avanti poi flettiti sulle ginocchia in avanti e in basso, torna su e riparti con l’altra gamba. Per incrementare la difficoltà, mantieni due bottiglie piene in mano durante l'esecizio)", R.drawable.act001, "Balance", "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Bicipiti", "Seduto o in piedi. Prendi in mano un peso (esempio: bottiglia d’acqua piena) e posiziona le braccia lungo i fianchi. Porta la mano verso la spalla poi torna giù lentamente. Consiglio: tieni fermo il braccio dal gomito in su. Avvicina l’avambraccio al bicipite.", R.drawable.act002, "AertiSuperiori"));
        exercizeListEasy.add(new Exercize("Tricipiti","Poggia una sedia contro il muro o avvicinati ad una superficie stabile non più alta del tuo bacino. Posizionati di spalle alla superficie poi poggia le mani sul bordo. lettiti con le braccia verso il suolo poi spingi sulle mani per tornare su.", R.drawable.act003, "ArtiSuperiori"));
        exercizeListEasy.add(new Exercize("Ponte","Distenditi su una stuoia con le ginocchia piegate e i piedi ben poggiati. Solleva il bacino verso l’alto spingendo sui talloni poi torna giù lentamente. Contrai i glutei.", R.drawable.act004, "Balance", "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Polpacci","In piedi con appoggia frontale. Solleva i talloni poi torna giù lentamente. Potenziamento: zaino con pesi", R.drawable.act005, "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Addominali crunch","Distenditi su una stuoia con le ginocchia piegate e piedi ben poggiati, poi poggia le mani sulle cosce. Solleva le spalle dal suolo portando le mani verso l’apice delle ginocchia. Potenziamento: mantieni un peso in mano", R.drawable.act006, "Core"));
        exercizeListEasy.add(new Exercize("Addominali inversi","Distenditi su una stuoia poggiando bene la pianta dei piedi. Solleva gli arti inferiori verso l’alto poi torna giù lentamente.", R.drawable.act007, "Core"));
        exercizeListEasy.add(new Exercize("Double leg stretch","Sdraiati a pancia insù con braccia lungo i fianchi e gambe distese. Tendi le braccia verso le gambe, solleva leggermente le gambe con capo che guarda verso i piedi. Mantieni la zona lombare completamente poggiata al suolo contraendo l’addome. Mantieni la posizione per 5 sec. Fletti le ginocchia al petto ed abbracciale con le braccia. Ritorna lentamente alla posizione di partenza. (NB: prima di partire prendi un bel respiro ed espira lentamente quando mantieni la contrazione)", R.drawable.act008, "Core"));
        exercizeListEasy.add(new Exercize("Alzate frontali","Seduto o in piedi, braccia tese con due pesi in mano. Solleva le braccia avanti fin all’altezza delle spalle poi torna giù lentamente.", R.drawable.act010, "Core"));
        exercizeListEasy.add(new Exercize("Flessione dell'anca","Posizione supina: distenditi completamente su un tappetino. Solleva una gamba mantenendola tesa poi torna giù lentamente. Alterna destra e sinistra. In piedi: in piedi con appoggio frontale, solleva il ginocchio fino all’altezza del bacino poi torna giù lentamente. Alterna destra e sinistra.", R.drawable.act013, "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Rotazione del bacino","Distenditi su un tappetino con le ginocchia flesse e piedi poggiati al suolo. Oscilla lentamente di lato con le ginocchia aumentando man mano l’arco di movimento. Potenziamento: eseguilo con anche e ginocchia flesse a 90° e piedi sollevati.", R.drawable.act017, "Core"));
        exercizeListEasy.add(new Exercize("Hundreds","Distenditi su un tappetino con le ginocchia piegate e la pianta dei piedi ben poggiati. 1 step: contrai l’addome e tendi le braccia verso i piedi. 2 step: solleva le ginocchia verso il petto fino al livello delle anche. 3 step: oscilla con le braccia tese per 30 secondi. Progressione: dopo aver portato su le gambe, allunga le ginocchia.", R.drawable.act019, "Core"));
        exercizeListEasy.add(new Exercize("Piegamenti su panca","Poggia una sedia contro il muro poi posiziona le mani agli angoli della sedia e fai un ampio passo indietro avvicinando il petto alla sedia. Tieni la schiena dritta. Spingi forte sulle mani sollevando il petto dalla sedia poi torna giù lentamente.", R.drawable.act020, "ArtiSuperiori"));
        exercizeListEasy.add(new Exercize("Glutei","In piedi, di fronte una parete con le mani poggiate. Mantieni la gamba tesa poi spingila dietro; alterna destro e sinistro", R.drawable.act015, "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Doppia flessione dell'anca","Distenditi su una stuoia. Mantieni le gambe tese e sollevale verso l’alto, poi torna giù lentamente. Progressione: raggiunti i 90° solleva il bacino spingendo i piedi verso l’alto poi torna giù prima con il bacino poi con le gambe.", R.drawable.act023, "Core"));
        exercizeListEasy.add(new Exercize("One leg stretch","Distenditi completamente su una stuoia. Contrai l’addome sollevando capo, spalle e gambe dal suolo poi alterna la flessione di una gamba al petto.", R.drawable.act009, "Core"));
        exercizeListEasy.add(new Exercize("Alzate unilaterali","Siediti con un peso in mano. Solleva un braccio di lato fino all’altezza della spalla poi torna giù lentamente. Esegui la prossima serie con l’altro braccio.", R.drawable.act012, "ArtiSuperiori"));
        exercizeListEasy.add(new Exercize("Sitting forward band","Siediti su un tappetino con le gambe stese. Chinati sulle gambe lentamente portando le mani verso dei piedi. NB: durante il movimento espira lentamente.", R.drawable.act025, "Stretching"));
        exercizeListEasy.add(new Exercize("Squat libero","In piedi, piegati su le gambe tendendo la schiena diritta. Solleva le braccia durante la discesa poi abbassale mentre risali.", R.drawable.act026, "Balance", "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Tacco punta","In piedi, solleva i talloni, torna giù poi solleva le punte dei piedi.", R.drawable.act028, "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Triceps bow","Distenditi a pancia giù con appoggio posteriore sui piedi. Posizionati a sfinge con i gomiti poggiati all’altezza delle spalle e le mani avanti. Mantieni l’addome e i glutei contratti. Solleva i gomiti spingendo sulle mani poi torna giù lentamente. Facilitazione: se risulta troppo faticoso punta posteriormente sulle ginocchia sulle ginocchia.", R.drawable.act029, "ArtiSuperiori"));
        exercizeListEasy.add(new Exercize("Slanci posteriori a carponi","Posizionati a carponi. Spingi la gamba verso l’alto, alterna destro e sinistro.", R.drawable.act050, "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Piegamenti","Posizionati disteso a pancia in giù con le mani all’altezza delle spalle e appoggio posteriore sui metatarsi. Contrai l’addome. Spingi forte sulle mani sollevando il petto dal suolo poi torna giù lentamente. Facilitazione: punta sulle ginocchia.", R.drawable.act051, "ArtiSuperiori"));
        exercizeListEasy.add(new Exercize("Spostamento frontale del peso","In piedi. Sul posto, oscilla lentamente avanti e dietro con il corpo. Oppure, sul posto, sposta il peso avanti e indietro senza perdere l’equilibrio. NB: non slegare il bacino dal tronco.", R.drawable.ic_warmup_foreground, "Balance")); //todo no immagine
        exercizeListEasy.add(new Exercize("Equilibrio monopodalico","Avvicinati ad un appoggio come un corrimano o semplicemente ad una parete. Con le mani ai fianchi, solleva un piede e mantieni l’equilibrio per almeno 5 secondi o più. Alterna destro e sinistro. NB: assicurati di eseguire l’esercizio in sicurezza.", R.drawable.act033, "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Tandem stance","Avvicinati a una parete o a un appoggio. Posiziona un piede di fronte l’altro poi rimani in equilibrio per 30 secondi. Evoluzione: chiudi gli occhi. NB: allargare le braccia aiuta a trovare l’equilibrio, viceversa incrociare le mani al petto aumenta la difficoltà.", R.drawable.act031, "Balance"));
        exercizeListEasy.add(new Exercize("Espirazione contro resistenza","Procurati una bottiglietta di plastica e una cannuccia poi riempila a metà con acqua. Soffia con vigore e costanza nella cannuccia per almeno 10 secondi. Riposa 30 secondi prima di ricominciare.", R.drawable.ic_warmup_foreground, "Respirazione")); //todo no immagine
        exercizeListEasy.add(new Exercize("Respirazione in 4 tempi","Siediti e mantieni la schiena dritta. Prendi aria lentamente per almeno 5 secondi poi mantieni il respiro per 5 secondi, butta fuori l’aria lentamente per almeno 5 secondi poi attendi altri 5 secondi prima di ricominciare.", R.drawable.ic_warmup_foreground, "Respirazione")); //todo no immagine
        exercizeListEasy.add(new Exercize("Slanci laterali a carponi","A carponi con le mani all’altezza delle spalle, ginocchia all’altezza delle anche. Contrai l’addome. Solleva lateralmente la gamba poi torna giù lentamente. Alterna destra e sinistra.", R.drawable.act016, "ArtiInferiori"));
        exercizeListEasy.add(new Exercize("Alzate laterali", "Seduto o in piedi con due pesi in mano. Solleva le braccia di lato fino all’altezza delle spalle poi torna giù lentamente.", R.drawable.ic_warmup_foreground, "ArtiSuperiori")); //todo creare immagine
        exercizeListEasy.add(new Exercize("Twist","Stenditi su un tappetino con le ginocchia piegate e le dita delle mani incrociate. Mantieni l’addome contratto. Esecuzione: solleva schiena e gambe dal tappeto poi ruota con le spalle da un lato all’altro toccando ogni volta il tappetino con le mani. Incremento: mantieni in mano un peso (bottiglia di plastica piena)", R.drawable.ic_warmup_foreground, "Balance", "Core")); //todo fare immagine da file
        exercizeListEasy.add(new Exercize("Spostamento laterale del peso","In piedi. Sul posto, oscilla lentamente di lato con il corpo. Oppure sposta il peso a destra e sinistra senza perdere l’equilibrio. NB: non slegare il bacino dal tronco.", R.drawable.ic_warmup_foreground, "Balance"));
        exercizeListEasy.add(new Exercize("Passo laterale","In piedi: cammina di lato come se dovessi attraversare un corridoio molto stretto.", R.drawable.act032, "ArtiInferiori"));
        /*  exercizeListEasy.add(new Exercize("Scissors","", ""));
            exercizeListEasy.add(new Exercize("Shoulder abduction unilaterale","", ""));
            exercizeListEasy.add(new Exercize("Estensione dell'anca","", ""));
            exercizeListEasy.add(new Exercize("Step simulate","", ""));
            exercizeListEasy.add(new Exercize("Hip twist","Seduto a terra con mani poggiate dietro, tronco leggermente inclinato indietro e schiena dritta. Prendere un bel respiro, sollevare leggermente le gambe dal suolo e disegnare un grande cerchio con la punta dei piedi. (zona lombare sempre dritta)", R.drawable.act018));
        */
    }
}
