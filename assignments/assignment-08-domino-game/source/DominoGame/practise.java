
/**
 * Write a description of class practise here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class practise
{
     public TrafficLight (String state, int cycles) {
         this.state = state;
         this.cycles = cycles;
     }
     
     public int getCycles() {
         return this.cycles;
     }
     
     public boolean isGreen () {
         return this.state.equals("green");
     }
     
     public void change() {
         if(this.state.equals("green")){
             this.state = "red";
         }
         else {
             this.state = "green";
         }
         this.cycles ++;
     }
     
     public String toString() {
         UI.println( this.state + " " +this.cycles );
     }
     
     public String toString(){
         return this.state + " " + this.cycles;
     }
     
     Question6
     
     if(this.state.equals("green")) {
         count++;
     }
     
     if(light.isGreen()) {
         light.change();
     }
     
     if(light.state.equals("green")){
         count++;
     }
     
     if( !light.isGreen()) {
         
     }
     
     light.change();
     if(light.isGreen()){
         count++;
     }
     
     question7
     
     String lines =Files.readAllLines(Path.of((fname)));
     
     for( String line : lines) {
         Scanner sc = new Scanner();
         String state = sc.Next();
         int cycles = sc.NextInt();
         
         result.add(new TrafficLight (state, cycles));
     }
     }
     
     Public class Book {
         
         private String title;
         private int pages;
         
         public Book (String titles, int page) {
             this.title = titles;
             this.pages = page;
         }
         
         public int getPages() {
             return this.pages;
         }
         public boolean isLongBook () {
             return this.pages >300;
         }
         public void addPages (int extra) {
             this.pages = this.pages + extra;
         }
         public String toString() {
             return tittle + " " + pages;
         }
         
         ArrayList<Book> books = new ArrayList<>();
         
        public void add50Pages () {
            for (Book book : books) {
                 book.addPages(50);
            }
        }
         public int countLongBooks() {
             int count = 0;
             for (Book book : books) { 
             if(book.isLongBook) {
                 count++;
         }
         }
         return count;
        }
        public Book firstLongBook() {
            for (Book book : books) {
                if(book.isLongBook) {
                    return book;
                }
            }
            return null
        }
        public boolean allLongBooks() {
            int count = 0;
            for (Book book : books) {
                if(book.isLongBook) {
                    count++;
                }
            }
            if (count.equals(books.size())) {
                return true;
            }
        }
        
        
        public ArrayList<Book> loadBooks(String fname){
            ArrayList<Book> result = new ArrayList<> ();
            try {
                List<String> lines = Files.readAllLines(Path.of(fname);
                for(String line : lines) {
                    Scanner sc = new Scanner(line);
                    String tittle = sc.next();
                    int pages = sc.nextInt();
                    result.add(new Book (tittle, pages);
                }
            }
        }
    
}