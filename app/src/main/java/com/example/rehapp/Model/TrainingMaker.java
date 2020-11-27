package com.example.rehapp.Model;

import com.example.rehapp.R;

import java.util.ArrayList;
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

        public Exercize(String title, String description, int imageId){
            this.description=description;
            this.title=title;
            this.imageId = imageId;
        }

        public String getTitle() { return title; }

        public String getDescription() { return description; }

        public int getImageId() { return imageId; }
    }


    private void createList(){
        exercizeListHard.add(new Exercize("Bicipiti", "Braccia lungo i fianchi, gomito sempre attaccato al tronco. Portare la mano sulla spalla.", R.drawable.act002));
        exercizeListHard.add(new Exercize("Quadricipiti", "Da seduto sollevare alternativamente il piede destro e il piede sinistro.", R.drawable.act036));
        exercizeListHard.add(new Exercize("Abduzione dell'anca", "In piedi sostenendosi ad un tavolo: portare una gamba di lato, poi l’altra. Seduto: aprire e chiudere le cosce.", R.drawable.act022));
        exercizeListHard.add(new Exercize("Squat", "In piedi, sostenendosi ad un tavolo: sollevare le braccia in avanti e piegare le anche e le ginocchia per andare giù tenendo il tronco perpendicolare al suolo, le ginocchia si spostano in avanti e il bacino indietro durante la discesa, in un movimento sinergico.", R.drawable.act026));
        exercizeListHard.add(new Exercize("Polpacci", "Da seduto, posizionare un peso sopra le ginocchia (bottiglia). con le ginocchia a 90° spingere sulle punte sollevando i talloni e tornare giù lentamente.", R.drawable.act005));  // todo immagine non appropriata
        exercizeListHard.add(new Exercize("Flessione dell'anca da seduto", "Seduti: Sollevare il ginocchio verso l’alto. Prima uno poi l’altro.", R.drawable.act013));
        exercizeListHard.add(new Exercize("Flessione/estensione di braccia alternata", "Con un peso in mano (bottiglia h2o da 1L o 2L) flettere il braccio destro e contemporaneamente estendere il sinistro e viceversa. Il ritmo deve essere sostenuto.", R.drawable.ic_warmup_foreground)); //todo
        exercizeListHard.add(new Exercize("Alzata di spalle", "Da seduti, sollevare le spalle verso le orecchie, mantenere almeno 3 secondi poi tornare giù lentamente.", R.drawable.act040));
        exercizeListHard.add(new Exercize("Roll down", "Da seduto a terra a gambe flesse, scendere lentamente verso il terreno appoggiando una dopo l’altra, le parti della colonna: sacro, lombare, dorsale e cervicale. Espirare durante l’esercizio", R.drawable.act041));
        exercizeListHard.add(new Exercize("Flessioni laterali", "Da seduto: inclinare il tronco prima da un lato poi dall’altro", R.drawable.act042));
        exercizeListHard.add(new Exercize("Cerchi con un braccio", "Abdurre il braccio a circa 60° e disegnare cerchi con il pugno (ruotare) per 20 secondi. Eseguirlo il entrambi i sensi (avanti e dietro) prima di cambiare arto.", R.drawable.act043));
        exercizeListHard.add(new Exercize("Flessione del braccio in diagonale", "Prendere in mano un peso. Con la mano poggiata sulla coscia controlaterale e il braccio ben esteso tracciare una diagonale verso l’alto e verso l’esterno. Può essere fatto terminando la serie con un braccio e poi eseguirla con l’altro oppure in maniera alternata.", R.drawable.act044));
        exercizeListHard.add(new Exercize("Croce", "Con un peso, sollevare lateralmente le braccia fin all’altezza delle spalle. Mantenere la posizione per circa 10- 15 secondi. Usare piccoli pesi (libri, utensili ecc) e incrementare gradualmente", R.drawable.act045));
        exercizeListHard.add(new Exercize("Spine twist", "Preparazione: gambe distese, braccia abdotte ed extra-ruotate, spalle lontane dalle orecchie, colonna in autoallungamento. Inspirare ed aumentare l’autoallungamento. Sviluppo: espirando ruotare immaginando di essere un cavatappi che avvita in direzione del soffitto, portare una spalla indietro creando una torsione dorsale. Tornare al centro inspirando. Ripetere dall’altro lato.", R.drawable.act046));
        exercizeListHard.add(new Exercize("Angel wings", "In posizione prona: braccia abdotte, gomiti flessi, mani poco sopra la testa e palmi verso il basso. Portare le mani su estendendo i gomiti lentamente e tornare alla posizione di partenza.", R.drawable.act047));
        exercizeListHard.add(new Exercize("Contrazione isometrica del quadricipite", "Posizione semi-supina, posizionare una bottiglia o un asciugamano arrotolato sotto il ginocchio. Schiacciare forte sollevando il tallone dalla superficie e tenere la contrazione per almeno 10 secondi.", R.drawable.act049));
        exercizeListHard.add(new Exercize("Respirazione in 4 tempi", "Scomporre il ciclo respiratorio in 4 fasi: inspirazione, pausa in apnea di 5 sec, espirazione prolungata, pausa in apnea di 5 sec", R.drawable.ic_warmup_foreground));
        /*  exercizeListUpTo6.add(new Exercize("Respirazione diaframmatica", "", ""));
            exercizeListUpTo6.add(new Exercize("Swan dive", "", ""));
            exercizeListUpTo6.add(new Exercize("Elevazione spalla", "", ""));
            exercizeListUpTo6.add(new Exercize("Esercizi calistenici da seduto", "", ""));
        */


        exercizeListMedium.add(new Exercize("Flessione/estensione di braccia alternata", "Con un peso in mano (bottiglia h2o da 1L o 2L) flettere il braccio destro e contemporaneamente estendere il sinistro e viceversa. Il ritmo deve essere sostenuto.", R.drawable.ic_warmup_foreground)); //todo no immagine
        exercizeListMedium.add(new Exercize("Bicipiti", "Braccia lungo i fianchi, gomito sempre attaccato al tronco. Portare la mano sulla spalla.", R.drawable.act002));
        exercizeListMedium.add(new Exercize("Ponte","Sdraiarsi in posizione supina, piegare le ginocchia con i piedi ben poggiati a terra. Portare il bacino insù contraendo i glutei e spingendo sui talloni. (mettere un peso sull’ addome per aggiungere resistenza).", R.drawable.act004));
        exercizeListMedium.add(new Exercize("Polpacci","Spingere forte con la punta dei piedi sollevando i talloni. Incremento: pesi in mano.", R.drawable.act005));
        exercizeListMedium.add(new Exercize("Alzata dalla sedia","Seduto su una sedia, schiena dritta, braccia incrociate al petto con mani sulle spalle controlaterali, piedi ben poggiati al suolo. Ispirare, inclinarsi in avanti con il tronco fin quando il bacino inizia a sollevarsi dalla sedia, espirare e spingersi sulle gambe per alzarsi dalla sedia. Ritorno morbido sulla sedia, ripartire.", R.drawable.act030));
        exercizeListMedium.add(new Exercize("Addominali inversi","Sdraiarsi a pancia insù, ginocchia piegate, piedi ben poggiati a terra. Portare le gambe semiflesse verso il soffitto e tornare alla posizione di partenza.", R.drawable.act007));
        exercizeListMedium.add(new Exercize("Rotazione del bacino","Supino: anche e ginocchia flesse, piedi poggiati al suolo. Oscillare lentamente le ginocchia prima a destra poi a sinistra fino a raggiungere gradualmente il suolo. Progressione: con anche e ginocchia flesse a 90° e piedi sollevati.", R.drawable.act017));
        exercizeListMedium.add(new Exercize("Piegamenti su panca","Poggiare le mani all’altezza delle spalle su un tavolo basso o una sedia vicina al muro. Fare un passo indietro inclinandosi verso il tavolo. Tenere la schiena dritta. Spingere forte, e tornare giù lentamente.", R.drawable.act020));
        exercizeListMedium.add(new Exercize("Piegamenti con ginocchia poggiate","In posizione prona, poggiare le mani a terra all’altezza delle spalle ma un po' più larghe, puntare le ginocchia. Per raddrizzare la colonna contrarre l’addome ruotando il bacino indietro. Spingere forte sollevando il petto dal suolo, e scendere giù lentamente.", R.drawable.act021));
        exercizeListMedium.add(new Exercize("Abduzione dell'anca", "In piedi: portare una gamba di lato, poi l’altra.", R.drawable.act022));
        exercizeListMedium.add(new Exercize("Flessione dell'anca","Posizione supina: sollevare arto esteso verso l’alto o portare il ginocchio verso il tronco. Prima uno poi l’altro. In piedi: sollevare il ginocchio verso il bacino. Prima uno poi l’altro.", R.drawable.act013));
        exercizeListMedium.add(new Exercize("Addominali obliqui","A terra, gambe flesse, busto inclinato di 45° e addome ben contratto. Ruotare il busto prima verso un lato, poi l’altro. Incremento: mantenere in mano un peso (bottiglia 2L).", R.drawable.act034));
        exercizeListMedium.add(new Exercize("Anti-retro versione del bacino","Supino, con tutta la colonna ben poggiata al suolo. Inarcare leggermente la zona lombare e ritornare alla posizione di partenza. Seduto, colonna perpendicolare al suolo. Inarcare la zona lombare portando avanti l’ombelico, ritornare portando in dentro l’ombelico, curvando indietro la lombare e chiudendo leggermente il petto. NB: le spalle e il capo devono restare sempre nella stessa posizione. Non si muovono né avanti né indietro. Carponi: mani e ginocchia alla larghezza delle spalle con le anche e ginocchia a 90° (ginocchia sotto il bacino). Curvare la schiena verso l’alto portando la lombare su, tornare giù fino ad inarcare la lombare verso il basso (ombelico verso l’alto, ombelico verso il basso)", R.drawable.act037));
        exercizeListMedium.add(new Exercize("Roll down","Da seduto a terra a gambe flesse, scendere lentamente verso il terreno appoggiando una dopo l’altra, le parti della colonna: sacro, lombare, dorsale e cervicale. NB: espirare durante l’esercizio.", R.drawable.act041));
        exercizeListMedium.add(new Exercize("Slanci laterali a carponi","Carponi: mani e ginocchia alla larghezza delle spalle. Le anche e le ginocchia sono flesse a 90°. Contrarre l’addome. Sollevare la gamba lateralmente. Alternare destra e sinistra.", R.drawable.act016));
        exercizeListMedium.add(new Exercize("Sitting forward band","Seduto a terra: tendere le mani verso i piedi e scendere in avanti buttando fuori l’aria (raggiungere gradualmente i piedi con le ginocchia ben poggiate a terra).", R.drawable.act025));
        exercizeListMedium.add(new Exercize("Tacco punta","In piedi: elevarsi sulle punte dei piedi sollevando i talloni e tornare giù sui talloni sollevando le punte dal suolo.", R.drawable.act028));
        exercizeListMedium.add(new Exercize("Step up","In piedi di fronte ad un gradino. Salire con il piede dx sul gradino fino ad estendere tutto l’arto e scendere con l’altro piede. Ripartire con il piede sn. Incremento: una volta su sollevare il ginocchio controlaterale. Ulteriore progressione: gradone più alto.", R.drawable.act027));
        exercizeListMedium.add(new Exercize("Tricipiti","Poggiare una sedia o una panca contro muro. Mettere entrambe le mani sul bordo di una panca posta dietro di sé. Le mani sono rivolte verso il corpo con le dita che sporgono sul bordo e i palmi aderenti alla panca. Le gambe possono essere estese o leggermente piegate e i talloni puntati a terra. Piegare le braccia e iperestendere le spalle fino a formare con entrambi i gomiti un angolo di 90°. Ritornare alla posizione di partenza estendendo il gomito (spingere simbolicamente la panca verso il basso) e ripetere.", R.drawable.act003));
        exercizeListMedium.add(new Exercize("Piegamenti a muro","Di fronte ad una parete posizionare le mani poco più larghe e poco più basse delle spalle a braccia stese. Fare un piccolo passo indietro, puntare i piedi e piegare i gomiti avvicinandosi al muro. Eseguire dei piegamenti. NB: più è profondo il passo indietro più forza richiede l’esercizio. Regolare il passo indietro in base alle proprie capacità.", R.drawable.act038));
        exercizeListMedium.add(new Exercize("Squat isometrico a muro","Appoggiarsi con la schiena ad una parete. Fare un passo avanti di circa 60 cm e allineare i piedi, che devono essere larghi quanto le spalle. Fare scivolare la schiena verso il basso contro la parete finché le cosce non sono parallele al suolo (art. ginocchio a 90°). Rimanere in questa posizione per 15-20 secondi tenendo l’addome contratto, poi tornare su raddrizzando le gambe sempre con la schiena contro la parete.", R.drawable.act039));
        exercizeListMedium.add(new Exercize("Spostamento laterale del peso","in piedi con i piedi pari e talloni quasi uniti. Oscillare leggermente lateralmente mantenendo spalle bacino e ginocchia allineati. NB: eseguire vicino ad una parete per maggiore sicurezza", R.drawable.ic_warmup_foreground));  //todo no immagine
        exercizeListMedium.add(new Exercize("Tandem stance","Avvicinarsi ad un tavolo o una sedia o un angolo della stanza, tenersi e posizionare un piede davanti all’altro. Cercare l’equilibrio senza tenersi. Respirare. Con calma provare a chiudere gli occhi. NB: allargare le braccia aiuta a trovare l’equilibrio, viceversa incrociare le mani al petto diminuisce la stabilità. Incremento: oscillare lateralmente con il bacino (ad occhi aperti)", R.drawable.act031));
        exercizeListMedium.add(new Exercize("Passo laterale","Camminare di lato, divaricando e avvicinando le gambe.", R.drawable.act032));
        exercizeListMedium.add(new Exercize("Espirazione contro resistenza","Materiale: bottiglietta da 50cl, cannuccia. Riempire 1/3 di bottiglia con acqua. Infilare una cannuccia. Soffiare energicamente dentro la cannuccia producendo bollicine per circa 10 sec (almeno 5). Incremento: liquidi più viscosi. Es: olio", R.drawable.ic_warmup_foreground)); //todo no immagine
        exercizeListMedium.add(new Exercize("Respirazione in 4 tempi","Scomporre il ciclo respiratorio in 4 fasi: inspirazione, pausa in apnea di 5 sec, espirazione prolungata, pausa in apnea di 5 sec", R.drawable.ic_warmup_foreground)); //todo no immagine
        exercizeListMedium.add(new Exercize("respirazioni profonde","Prendere un lungo e profondo respiro gonfiando al massimo il petto (min 5 sec). Buttare fuori l’aria con le labbra socchiuse gonfiando le guance. NB: nell’espirazione si deve avvertire una resistenza alla fuoriuscita dell’aria", R.drawable.ic_warmup_foreground)); //todo no immaine
        /*  exercizeListMedium.add(new Exercize("Semi-affondi", "", ""));
            exercizeListMedium.add(new Exercize("Elevazione spalla","", ""));
            exercizeListMedium.add(new Exercize("Alzate posteriori a carponi","", ""));
            exercizeListMedium.add(new Exercize("Scapular adduction","", ""));
            exercizeListMedium.add(new Exercize("Shoulder abduction unilaterale","", ""));
            exercizeListMedium.add(new Exercize("Single leg stance","", ""));
            exercizeListMedium.add(new Exercize("Twist","", ""));
            exercizeListMedium.add(new Exercize("Slanci laterali a carponi","", ""));
            exercizeListMedium.add(new Exercize("Cammino sulle punte / cammino sui talloni","", ""));
            exercizeListMedium.add(new Exercize("Esercizi calistenici da seduto","", ""));
            exercizeListMedium.add(new Exercize("Windmill arms","", ""));
        */


        exercizeListEasy.add(new Exercize("Affondi","Piedi posizionati alla larghezza delle spalle. Fare un ampio passo in avanti piegando entrambe le gambe e andando giù. Tronco sempre perpendicolare al suolo. Il ginocchio che sta avanti non deve superare la punta del piede. Alternare destra e sinistra. (tenere due bottiglie piene per aggiungere resistenza)", R.drawable.act001));
        exercizeListEasy.add(new Exercize("Bicipiti", "Braccia lungo i fianchi, gomito sempre attaccato al tronco. Portare la mano sulla spalla.", R.drawable.act002));
        exercizeListEasy.add(new Exercize("Tricipiti","Poggiare una sedia o una panca contro muro. Mettere entrambe le mani sul bordo di una panca posta dietro di sé. Le mani sono rivolte verso il corpo con le dita che sporgono sul bordo e i palmi aderenti alla panca. Le gambe possono essere estese o leggermente piegate e i talloni puntati a terra. Piegare le braccia e iperestendere le spalle fino a formare con entrambi i gomiti un angolo di 90°. Ritornare alla posizione di partenza estendendo il gomito (spingere simbolicamente la panca verso il basso) e ripetere.", R.drawable.act003));
        exercizeListEasy.add(new Exercize("Ponte","Sdraiarsi in posizione supina, piegare le ginocchia con i piedi ben poggiati a terra. Portare il bacino insù contraendo i glutei e spingendo sui talloni. (mettere un peso sull’ addome per aggiungere resistenza).", R.drawable.act004));
        exercizeListEasy.add(new Exercize("Polpacci","Spingere forte con la punta dei piedi sollevando i talloni. Incremento: pesi in mano.", R.drawable.act005));
        exercizeListEasy.add(new Exercize("Addominali crunch","Sdraiarsi a pancia insù, ginocchia piegate, piedi ben poggiati. Braccia stese con mani poggiate vicino alle cosce. Con le mani raggiungere l’apice delle ginocchia staccando testa e spalle dal suolo. (tenere peso in mano per aggiungere resistenza)", R.drawable.act006));
        exercizeListEasy.add(new Exercize("Addominali inversi","Sdraiarsi a pancia insù, ginocchia piegate, piedi ben poggiati a terra. Portare le gambe semiflesse verso il soffitto e tornare alla posizione di partenza.", R.drawable.act007));
        exercizeListEasy.add(new Exercize("Double leg stretch","Sdraiarsi a pancia insù con braccia lungo i fianchi e gambe distese.  Tendere le braccia verso le gambe, sollevare leggermente le gambe con capo che guarda verso i piedi. Mantenere la zona lombare completamente poggiata al suolo contraendo l’addome. tenere per 5 sec. Flettere le ginocchia al petto ed abbracciarle con le braccia. Ritornare lentamente alla posizione di partenza. (NB: prima di partire prendere un bel respiro ed espirare lentamente quando si mantiene la contrazione)", R.drawable.act008));
        exercizeListEasy.add(new Exercize("Alzate frontali","Seduto o in piedi, braccio leggermente staccato dal tronco, gomito esteso ed un peso in mano. Sollevare il peso fino all’altezza della spalla.", R.drawable.act010));
        exercizeListEasy.add(new Exercize("Flessione dell'anca","Posizione supina: sollevare arto esteso verso l’alto o portare il ginocchio verso il tronco. Prima uno poi l’altro. In piedi: sollevare il ginocchio verso il bacino. Prima uno poi l’altro.", R.drawable.act013));
        exercizeListEasy.add(new Exercize("Rotazione del bacino","Supino: anche e ginocchia flesse, piedi poggiati al suolo. Oscillare lentamente le ginocchia prima a destra poi a sinistra fino a raggiungere gradualmente il suolo. Progressione: con anche e ginocchia flesse a 90° e piedi sollevati.", R.drawable.act017));
        exercizeListEasy.add(new Exercize("Hip twist","Seduto a terra con mani poggiate dietro, tronco leggermente inclinato indietro e schiena dritta. Prendere un bel respiro, sollevare leggermente le gambe dal suolo e disegnare un grande cerchio con la punta dei piedi. (zona lombare sempre dritta)", R.drawable.act018));
        exercizeListEasy.add(new Exercize("Hundred","Supino, ginocchia piegate, piedi poggiati. 1) step: contrarre l’addome spingendo le mani verso i piedi, flettere il capo e sollevare le scapole dal suolo. 2) step: flettere le anche e le ginocchia a 90° sollevando i piedi. 3) step: oscillare su e giù con le braccia tese. Cercare di completare 5 cicli respiratori. Progressione: estendere le ginocchia", R.drawable.act019));
        exercizeListEasy.add(new Exercize("Piegamenti su panca","Poggiare le mani all’altezza delle spalle su un tavolo basso o una sedia vicina al muro. Fare un passo indietro inclinandosi verso il tavolo. Tenere la schiena dritta. Spingere forte, e tornare giù lentamente.", R.drawable.act020));
        exercizeListEasy.add(new Exercize("Glutei","In piedi, di fronte alla parete poggiando le mani. Spingere la gamba indietro in maniera alternata (prima dx poi sn).", R.drawable.act015));
        exercizeListEasy.add(new Exercize("Doppia flessione dell'anca","Supino, schiena ben poggiata. Mentre si tiene il tratto lombare ben a contatto con la superficie portare entrambe le gambe su fino ad un massimo di 90° di flessione di anca. Progressione: raggiunti i 90° spingere i piedi verso l’alto sollevando il bacino. Tornare lentamente giù.", R.drawable.act023));
        exercizeListEasy.add(new Exercize("One leg stretch","Sdraiarsi a pancia insù con braccia lungo i fianchi e gambe distese. Tendere le braccia verso le gambe, flettere al petto prima un arto e tornare alla posizione di partenza, poi flettere l’altro.", R.drawable.act009));
        exercizeListEasy.add(new Exercize("Alzate unilaterali","Seduto o in piedi, gomito esteso ed un peso in mano. Sollevare prima il destro e tornare giù, poi sollevare il sinistro.", R.drawable.act012));
        exercizeListEasy.add(new Exercize("Sitting forward band","Seduto a terra: tendere le mani verso i piedi e scendere in avanti buttando fuori l’aria (raggiungere gradualmente i piedi con le ginocchia ben poggiate a terra).", R.drawable.act025));
        exercizeListEasy.add(new Exercize("Squat libero","In piedi: sollevare le braccia in avanti e piegare le anche e le ginocchia per andare giù tenendo il tronco perpendicolare al suolo. NB: le ginocchia si spostano in avanti e il bacino indietro durante la discesa, in un movimento sinergico.", R.drawable.act026));
        exercizeListEasy.add(new Exercize("Tacco punta","In piedi: elevarsi sulle punte dei piedi sollevando i talloni e tornare giù sui talloni sollevando le punte dal suolo.", R.drawable.act028));
        exercizeListEasy.add(new Exercize("Triceps bow","Posizione di partenza: plank. Poggiare i gomiti e gli avambracci sul terreno con i palmi delle mani giù, con i gomiti all’altezza delle spalle, e i piedi uniti. Sollevare il bacino in modo tale che testa spalle tronco e bacino siano allineati. contrarre l’addome per far sì che il tratto lombare non vada in iperlordosi. Esecuzione: sollevare i gomiti fino ad estendere totalmente le braccia e tornare lentamente giù.  NB:  tenere sempre contratto l’addome; se il tratto lombare cede in iperlordosi, fermarsi. Facilitazione: appoggio posteriore sulle ginocchia.", R.drawable.act029));
        exercizeListEasy.add(new Exercize("Slanci posteriori a carponi","A carponi, spingere la gamba dietro alternando destra e sinistra.", R.drawable.act050));
        exercizeListEasy.add(new Exercize("Piegamenti","In posizione prona, poggiare le mani a terra all’altezza delle spalle ma un po' più larghe. Per addrizzare la colonna contrarre l’addome ruotando il bacino indietro. Spingere forte sollevando il petto dal suolo, e scendere giù lentamente. Facilitazione: puntare le ginocchia. Incremento: avvicinare le mani", R.drawable.act051));
        exercizeListEasy.add(new Exercize("Spostamento frontale del peso","In piedi con i piedi pari e talloni quasi uniti. Oscillare leggermente avanti e indietro mantenendo spalle bacino e ginocchia allineati.", R.drawable.ic_warmup_foreground)); //todo no immagine
        exercizeListEasy.add(new Exercize("Equilibrio monopodalico","Posizione dietro una sedia, di fronte ad un muro o vicino a un tavolo. Sollevare lentamente un piede dal terreno fino a raggiungere 90° gradi di flessione di anca. Mantenere la posizione per 5 sec. Poggiare di nuovo il piede e ripartire con l’altra gamba. NB: eseguire sempre l’esercizio in sicurezza: tenersi ad una superficie. Facilitazione: sollevare il piede portando il tallone indietro.", R.drawable.act033));
        exercizeListEasy.add(new Exercize("Tandem stance","Avvicinarsi ad un tavolo o una sedia o un angolo della stanza, tenersi e posizionare un piede davanti all’altro. Cercare l’equilibrio senza tenersi. Respirare. Con calma provare a chiudere gli occhi. NB: allargare le braccia aiuta a trovare l’equilibrio, viceversa incrociare le mani al petto diminuisce la stabilità. Incremento: oscillare lateralmente con il bacino (ad occhi aperti)", R.drawable.act031));
        exercizeListEasy.add(new Exercize("Espirazione contro resistenza","Materiale: bottiglietta da 50cl, cannuccia. Riempire 1/3 di bottiglia con acqua. Infilare una cannuccia. Soffiare energicamente dentro la cannuccia producendo bollicine per circa 10 sec (almeno 5). Incremento: liquidi più viscosi. Es: olio", R.drawable.ic_warmup_foreground)); //todo no immagine
        exercizeListEasy.add(new Exercize("Respirazione in 4 tempi","Scomporre il ciclo respiratorio in 4 fasi: inspirazione, pausa in apnea di 5 sec, espirazione prolungata, pausa in apnea di 5 sec", R.drawable.ic_warmup_foreground)); //todo no immagine
        /*  exercizeListEasy.add(new Exercize("Twist","", ""));
            exercizeListEasy.add(new Exercize("Spostamento laterale del peso","", ""));
            exercizeListEasy.add(new Exercize("Passo laterale","", ""));
            exercizeListEasy.add(new Exercize("Scissors","", ""));
            exercizeListEasy.add(new Exercize("Shoulder abduction unilaterale","", ""));
            exercizeListEasy.add(new Exercize("Slanci laterali a carponi","", ""));
            exercizeListEasy.add(new Exercize("Estensione dell'anca","", ""));
            exercizeListEasy.add(new Exercize("Step simulate","", ""));
        */
    }
}
