package com.example.javaLearningApp.QuestionsFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.javaLearningApp.R
import com.example.javaLearningApp.databinding.FragmentQuestionsBinding

class QuestionsFragment : Fragment() {

    private var mList = ArrayList<QueData>()
    private lateinit var adapter: LanguageAdapter
    private lateinit var codingQueAdapter: CodingQueAdapter
    private lateinit var db: NotesDBHelper
    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using View Binding
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = NotesDBHelper(requireContext())

        binding.cardLayout1.setOnClickListener {
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter = LanguageAdapter(mList, requireContext())
            binding.recyclerView.adapter = adapter
            loadNotes()
            binding.interviewQueText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.codingQueText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            binding.cardLayout1.setBackgroundResource(R.drawable.gredient_background_program)
            binding.cardLayout2.setBackgroundResource(R.drawable.bgcard)

        }

        binding.cardLayout2.setOnClickListener {
            binding.interviewQueText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            binding.codingQueText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.cardLayout2.setBackgroundResource(R.drawable.gredient_background_program)
            binding.cardLayout1.setBackgroundResource(R.drawable.bgcard)
            //binding.recyclerView.visibility=View.GONE
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val listCoding= mutableListOf<QueData>()
            listCoding.add(QueData(0, "Why is multiple inheritance not supported in Java?", "Java does not support multiple inheritance because it can lead to complexities and ambiguities, such as the diamond problem."))
            listCoding.add(QueData(0, "Why is multiple inheritance not supported in Java?", "Java does not support multiple inheritance because it can lead to complexities and ambiguities, such as the diamond problem."))
            codingQueAdapter = CodingQueAdapter(requireContext(),listCoding)
            binding.recyclerView.adapter = codingQueAdapter
        }

        // Hide ActionBar
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        sharedPreferences = requireActivity().getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        // Check if questions have been inserted previously
        val isQuestionsInserted = sharedPreferences.getBoolean("QUESTIONS_INSERTED", false)
        if (!isQuestionsInserted) {

            addAllNotesManually()
            // Mark questions as inserted in SharedPreferences
            sharedPreferences.edit().putBoolean("QUESTIONS_INSERTED", true).apply()
        }
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LanguageAdapter(mList, requireContext())
        binding.recyclerView.adapter = adapter
        loadNotes()
    }

    private fun addAllNotesManually() {
        val notesList = mutableListOf(
            QueData(0, "Why is multiple inheritance not supported in Java?", "Java does not support multiple inheritance because it can lead to complexities and ambiguities, such as the diamond problem."),
            QueData(0, "What is Kotlin?", "Kotlin is a cross-platform, statically typed, general-purpose programming language with type inference."),
            QueData(1, "What is Java?", "Java is a high-level, object-oriented programming language developed by Sun Microsystems."),
        QueData(2, "Why is Java platform-independent?", "Java is platform-independent because of its bytecode. The Java compiler converts the Java code into bytecode, which can run on any platform with a Java Virtual Machine (JVM)."),
        QueData(3, "What is the difference between JDK, JRE, and JVM?", "JDK (Java Development Kit) is a software development kit that includes tools for developing Java applications. JRE (Java Runtime Environment) is an environment in which Java programs run. JVM (Java Virtual Machine) is an abstract machine that provides the runtime environment for Java bytecode."),
        QueData(4, "What are the main features of Java?", "The main features of Java include platform independence, object-oriented programming, robustness, security, and portability."),
        QueData(5, "What is the difference between == and equals() in Java?", "== is used to compare reference equality (whether two objects reference the same memory location), while equals() is used to compare object content (whether the values of the objects are equal)."),
        QueData(6, "Explain the difference between an interface and an abstract class in Java.", "An interface in Java is a blueprint of a class that defines a set of methods without implementations. An abstract class is a class that cannot be instantiated and may contain abstract methods and concrete methods."),
        QueData(7, "What is the difference between checked and unchecked exceptions in Java?", "Checked exceptions are checked at compile-time and must be either caught or declared in the method signature using the throws keyword. Unchecked exceptions are not checked at compile-time and do not need to be caught or declared."),
        QueData(8, "What is the purpose of the final keyword in Java?", "The final keyword in Java is used to restrict the user. It can be used with variables, methods, and classes. When a variable is declared as final, its value cannot be changed. When a method is declared as final, it cannot be overridden. When a class is declared as final, it cannot be subclassed."),
        QueData(9, "What is the difference between an array and an ArrayList in Java?", "An array in Java is a fixed-size data structure that stores elements of the same data type in contiguous memory locations. An ArrayList is a dynamic array that can grow or shrink in size dynamically and can store elements of different data types."),
        QueData(10, "What is the difference between a constructor and a method in Java?", "A constructor in Java is a special type of method that is called when an object of a class is instantiated. It is used to initialize the object. A method in Java is a collection of statements that performs a specific task."),
        QueData(11, "What is method overloading in Java?", "Method overloading in Java is the ability to define multiple methods with the same name but different parameters in the same class. The compiler determines which method to call based on the number and type of arguments passed to it."),
        QueData(12, "What is method overriding in Java?", "Method overriding in Java is the ability of a subclass to provide a specific implementation of a method that is already defined in its superclass. It is used to achieve runtime polymorphism."),
        QueData(13, "What is inheritance in Java?", "Inheritance in Java is the mechanism by which one class inherits the properties and behaviors of another class. It allows a class to reuse code and establish a relationship between classes."),
        QueData(14, "What is encapsulation in Java?", "Encapsulation in Java is the process of wrapping data (variables) and code (methods) together as a single unit. It restricts direct access to the data and allows access to the data only through the methods."),
        QueData(15, "What is abstraction in Java?", "Abstraction in Java is the process of hiding the implementation details and showing only the essential features of an object. It is achieved using abstract classes and interfaces."),
        QueData(16, "What is polymorphism in Java?", "Polymorphism in Java is the ability of an object to take on multiple forms. It allows methods to do different things based on the object that invokes them. There are two types of polymorphism in Java: compile-time polymorphism (method overloading) and runtime polymorphism (method overriding)."),
        QueData(17, "What is a constructor in Java?", "A constructor in Java is a special type of method that is called when an object of a class is created. It is used to initialize the object and allocate memory."),
        QueData(18, "What is a class in Java?", "A class in Java is a blueprint for creating objects. It defines the data and methods that all objects of the class will have."),
        QueData(19, "What is an object in Java?", "An object in Java is an instance of a class. It has state (attributes) and behavior (methods)."),
        QueData(20, "What is a package in Java?", "A package in Java is a namespace that organizes a set of related classes and interfaces. It helps in avoiding naming conflicts and provides access protection."),
        QueData(21, "What is the Java Virtual Machine (JVM)?", "The Java Virtual Machine (JVM) is an abstract machine that provides the runtime environment for Java bytecode. It interprets the bytecode and executes the Java program."),
        QueData(22, "What is bytecode in Java?", "Bytecode in Java is the intermediate representation of the source code that is generated by the Java compiler. It is platform-independent and can be executed by any Java Virtual Machine (JVM)."),
        QueData(23, "What is the difference between a compiler and an interpreter?", "A compiler translates the entire source code into machine code before execution, whereas an interpreter translates the source code line by line during execution."),
        QueData(24, "What are wrapper classes in Java?", "Wrapper classes in Java are classes that allow primitive data types to be treated as objects. They provide methods for converting primitive data types into objects and vice versa."),
        QueData(25, "What is autoboxing and unboxing in Java?", "Autoboxing is the automatic conversion of primitive data types into their corresponding wrapper classes, and unboxing is the automatic conversion of wrapper classes into their corresponding primitive data types."),
        QueData(26, "What is the difference between the String, StringBuilder, and StringBuffer classes in Java?", "The String class is immutable, meaning its value cannot be changed once it is created. StringBuilder and StringBuffer are mutable, meaning their values can be changed. StringBuffer is thread-safe, whereas StringBuilder is not."),
        QueData(27, "What is the difference between static and non-static methods in Java?", "Static methods belong to the class itself and can be called without creating an instance of the class. Non-static methods belong to individual objects of the class and can only be called on instances of the class."),
        QueData(28, "What is the Java API?", "The Java API (Application Programming Interface) is a collection of prewritten packages, classes, and interfaces that provide ready-to-use functionality to Java programs."),
        QueData(29, "What is the main method in Java?", "The main method is the entry point of a Java program. It is the first method that is called when the program is executed."),
        QueData(30, "What is the difference between public, private, protected, and default access modifiers in Java?", "Public members can be accessed from any other class. Private members can only be accessed within the same class. Protected members can be accessed within the same package or by subclasses. Default members can only be accessed within the same package."),
        QueData(31, "What is the purpose of the this keyword in Java?", "The this keyword in Java refers to the current object instance. It is used to differentiate between instance variables and local variables with the same name, as well as to invoke constructors from other constructors in the same class."),
        QueData(32, "What is the purpose of the super keyword in Java?", "The super keyword in Java is used to refer to the superclass of the current object instance. It is used to access superclass methods, constructors, and instance variables."),
        QueData(33, "What are static variables and methods in Java?", "Static variables and methods belong to the class itself rather than individual objects of the class. They can be accessed using the class name."),
        QueData(34, "What is the final keyword in Java?", "The final keyword in Java is used to restrict the user. It can be used with variables, methods, and classes. When a variable is declared as final, its value cannot be changed. When a method is declared as final, it cannot be overridden. When a class is declared as final, it cannot be subclassed."),
        QueData(35, "What is the difference between static and final variables in Java?", "Static variables belong to the class itself and are shared among all instances of the class. Final variables are constants and cannot be changed once initialized."),
        QueData(36, "What is the difference between inheritance and composition in Java?", "Inheritance is an 'is-a' relationship, where a class inherits properties and behaviors from another class. Composition is a 'has-a' relationship, where a class contains objects of other classes as members."),
        QueData(37, "What is the difference between method hiding and method overriding in Java?", "Method hiding occurs when a static method in a subclass has the same name and signature as a static method in the superclass. Method overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass."),
        QueData(38, "What is the diamond problem in Java?", "The diamond problem occurs in multiple inheritance when a subclass inherits from two superclasses that have a common ancestor. It can lead to ambiguity in method resolution."),
        QueData(39, "What are access specifiers in Java?", "Access specifiers in Java are keywords that define the accessibility (visibility) of classes, methods, and variables. The main access specifiers in Java are public, private, protected, and default."),
        QueData(40, "What is the difference between the default and protected access specifiers in Java?", "Default members can only be accessed within the same package, whereas protected members can also be accessed by subclasses outside the package."),
        QueData(41, "What is the difference between abstract classes and interfaces in Java?", "Abstract classes can have both abstract and concrete methods, whereas interfaces can only have abstract methods. A class can implement multiple interfaces but can only inherit from one abstract class."),
        QueData(42, "What are lambda expressions in Java?", "Lambda expressions in Java are anonymous functions that can be treated as objects. They allow you to pass functions as arguments and define concise inline implementations of functional interfaces."),
        QueData(43, "What are functional interfaces in Java?", "Functional interfaces in Java are interfaces that contain only one abstract method. They are used to implement lambda expressions and provide a single unified way to define and use functional interfaces."),
        QueData(44, "What is the purpose of the default method in Java interfaces?", "The default method in Java interfaces allows you to add new methods to existing interfaces without breaking backward compatibility. It provides a default implementation that can be overridden by implementing classes."),
        QueData(45, "What is the purpose of the static method in Java interfaces?", "The static method in Java interfaces allows you to define utility methods that can be called directly on the interface without implementing it."),
        QueData(46, "What are the advantages of using Java over other programming languages?", "Some advantages of Java include its platform independence, object-oriented programming support, robustness, security features, and large community support."),
        QueData(47, "What is the Java Naming and Directory Interface (JNDI)?", "The Java Naming and Directory Interface (JNDI) is an API that provides naming and directory functionality to Java applications. It allows Java programs to access naming and directory services such as LDAP and DNS."),
        QueData(48, "What are annotations in Java?", "Annotations in Java are metadata that provide data about a program but do not directly affect the program's execution. They can be used to provide information to the compiler, runtime, or other tools."),
        QueData(49, "What is the difference between @Override and @Overload annotations in Java?", "@Override is used to indicate that a method is being overridden from a superclass, whereas @Overload is not a standard Java annotation."),
        QueData(50, "What is the purpose of the transient keyword in Java?", "The transient keyword in Java is used to indicate that a variable should not be serialized when the object is written to an ObjectOutputStream. It is used to exclude certain fields from the serialization process.")
        )



        for (note in notesList) {
            db.insertNote(note.question, note.answer)
        }
    }

    private fun loadNotes() {
        val notes = db.getAllNotes()
        adapter.updateNotes(notes)
    }
}

class LanguageAdapter(private var mList: List<QueData>, val context: Context) :
    RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.titleTv)
        val langDescTv: TextView = itemView.findViewById(R.id.langDesc)
        val constraintLayout: RelativeLayout = itemView.findViewById(R.id.constraintLayout)

        fun collapseExpandedView() {
            langDescTv.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.each_item_interview_que, parent, false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val languageData = mList[position]

        holder.titleTv.text = languageData.question
        holder.langDescTv.text = languageData.answer

        val isExpandable: Boolean = languageData.isExpandable
        holder.langDescTv.visibility = if (isExpandable) View.VISIBLE else View.GONE

        setAnimation(holder.itemView, position)

        holder.constraintLayout.setOnClickListener {
            isAnyItemExpanded(position)
            languageData.isExpandable = !languageData.isExpandable
            notifyItemChanged(position, Unit)
        }
    }

    private fun isAnyItemExpanded(position: Int) {
        val temp = mList.indexOfFirst {
            it.isExpandable
        }
        if (temp >= 0 && temp != position) {
            mList[temp].isExpandable = false
            notifyItemChanged(temp, 0)
        }
    }

    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty() && payloads[0] == 0) {
            holder.collapseExpandedView()
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }



    fun updateNotes(notes: List<QueData>) {
        mList = notes
        notifyDataSetChanged()
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        val slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
        viewToAnimate.startAnimation(slideIn)
    }
}

class CodingQueAdapter(private val context: Context, private val queDataList: List<QueData>) :
    RecyclerView.Adapter<CodingQueAdapter.QueDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueDataViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.each_item_coding_que, parent, false)
        return QueDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: QueDataViewHolder, position: Int) {
        val queData = queDataList[position]
        holder.bind(queData)
    }

    override fun getItemCount(): Int {
        return queDataList.size
    }

    inner class QueDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionTextView: TextView = itemView.findViewById(R.id.question_coding)
       // private val answerTextView: TextView = itemView.findViewById(R.id.answerTextView)

        fun bind(queData: QueData) {
            questionTextView.text = queData.question
            //answerTextView.text = queData.answer
        }
    }
}
