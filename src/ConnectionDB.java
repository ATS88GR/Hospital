import java.sql.*;


public class ConnectionDB {
    public static Connection con;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException {
        con = null;
        Class.forName("org.sqlite.JDBC");  // Загрузка драйвера
        con = DriverManager.getConnection("jdbc:sqlite:Hospital.s3db"); // Подключение/Создание базы

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException {
        statmt = con.createStatement();
        statmt.execute("CREATE TABLE if not exists 'Departments' (" +
                "'Id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'Building' INT NOT NULL CHECK(Building>0 AND Building<5), " +
                "'Financing' MONEY NOT NULL DEFAULT 0 CHECK (Financing>=0), " +
                "'Name' NVCHAR(100) NOT NULL UNIQUE CHECK (length('Name') > 0)"+
                ");");
        statmt.execute("CREATE TABLE if not exists 'Diseases' (" +
                "'Id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'Name' NVCHAR(100) NOT NULL UNIQUE CHECK (length('Name') > 0), " +
                "'Severity' INT DEFAULT 1 CHECK(Severity>0), " +
                "CHECK(Severity >0)"+
                ");");
        statmt.execute("CREATE TABLE if not exists 'Doctors' (" +
                "'Id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'Name' NVCHAR NOT NULL UNIQUE CHECK (length('Name') > 0), " +
                "'Phone' CHAR(15), " +
                "'Salary' MONEY CHECK(Salary > 0), " +
                "'Surname' NVARCHAR MAX NOT NULL CHECK (length('Surname') > 0)," +
                "CHECK (Salary>0)"+
                ");");
        statmt.execute("CREATE TABLE if not exists 'Examinations' (" +
                "'Id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'DayOfWeek' INT NOT NULL CHECK(DayOfWeek >0 AND DayOfWeek < 8), " +
                "'EndTime' TIME NOT NULL CHECK(EndTime > StartTime), " +
                "'Name' NVCHAR(100) NOT NULL UNIQUE CHECK (trim('name')!=''), " +
                "'StartTime' TIME NOT NULL CHECK(StartTime >=8.00 AND StartTime <=18.00)" +
                ");");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException
    {                  // вставка    в какую таблицу    в какие поля              что вставлять
        statmt.execute("INSERT INTO 'Departments'    ('building', 'financing', 'name') VALUES ('3', '102', 'Therapy'); ");
        statmt.execute("INSERT INTO 'Diseases'    ('name', 'severity') VALUES ('cansor', '10'); ");
        statmt.execute("INSERT INTO 'Doctors'   ('name', 'phone', 'salary', 'surname') VALUES ('Mark', '375339876543', '4561', 'Wolberg'); ");
        statmt.execute("INSERT INTO 'Examinations'  ('dayofweek', 'endtime', 'name', 'starttime') VALUES ('3', '12.00', 'Gistology', '08.20'); ");

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public static void ReadDB() throws ClassNotFoundException, SQLException
    {
         resSet = statmt.executeQuery("SELECT * FROM Doctors");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            String  surname = resSet.getString("surname");
            String  phone = resSet.getString("phone");
            double money = resSet.getDouble("salary");

            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "surname = " + surname );
            System.out.println( "phone = " + phone );
            System.out.println( "money = " + money );
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        con.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

}