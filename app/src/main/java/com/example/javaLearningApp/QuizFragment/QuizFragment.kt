package com.example.javaLearningApp.QuizFragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.javaLearningApp.R
import com.example.javaLearningApp.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle button click
        binding.startQuizButton.setOnClickListener {
            // Check if a difficulty level and number of questions are selected
            val selectedDifficulty = getSelectedDifficulty()
            val selectedQuestionCount = getSelectedQuestionCount()

            if (selectedDifficulty != null && selectedQuestionCount != null) {
                // Both difficulty level and number of questions are selected

                // Create an Intent to start the activity
                val intent = Intent(requireContext(), QuizStartActivity::class.java)

                // Add extra data to the Intent
                intent.putExtra("quiz_number", getSelectedQuestionCount())
                intent.putExtra("difficulty_level", getSelectedDifficulty())

                // Start the activity
                startActivity(intent)
            } else {
                // Display a toast message indicating that the user needs to select both difficulty level and number of questions
                Toast.makeText(requireContext(), "Please select both difficulty level and number of questions", Toast.LENGTH_SHORT).show()
            }
        }

        binding.layoutBeginnerCard.setOnClickListener {
            setSelectedDifficulty(binding.layoutBeginnerCard, binding.easyTextview)
        }

        binding.layoutIntermediateCard.setOnClickListener {
            setSelectedDifficulty(binding.layoutIntermediateCard, binding.mediumTextview)
        }

        binding.layoutExpertCard.setOnClickListener {
            setSelectedDifficulty(binding.layoutExpertCard, binding.expertTextview)
        }
        binding.que10Layout.setOnClickListener {
            setSelectedDifficultyForQue(binding.que10Layout, binding.que10)
        }

        binding.que15Layout.setOnClickListener {
            setSelectedDifficultyForQue(binding.que15Layout, binding.que15)
        }

        binding.que25Layout.setOnClickListener {
            setSelectedDifficultyForQue(binding.que25Layout, binding.que25)
        }



    }
    private fun getSelectedDifficulty(): String? {
        return when {
            binding.easyTextview.currentTextColor == ContextCompat.getColor(requireContext(), R.color.white) -> "easy"
            binding.mediumTextview.currentTextColor == ContextCompat.getColor(requireContext(), R.color.white) -> "medium"
            binding.expertTextview.currentTextColor == ContextCompat.getColor(requireContext(), R.color.white) -> "hard"
            else -> null
        }
    }

    private fun getSelectedQuestionCount(): Int? {
        return when {
            binding.que10.currentTextColor == ContextCompat.getColor(requireContext(), R.color.white) -> 10
            binding.que15.currentTextColor == ContextCompat.getColor(requireContext(), R.color.white) -> 15
            binding.que25.currentTextColor == ContextCompat.getColor(requireContext(), R.color.white) -> 25
            else -> null
        }
    }
    private fun setSelectedDifficulty(selectedLayout: RelativeLayout, selectedTextView: TextView) {
        // Set the background drawable to the selected layout
        selectedLayout.background = ContextCompat.getDrawable(requireContext(), R.drawable.gredient_copy_main)

        // Reset background drawable for other layouts
        if (selectedLayout != binding.layoutBeginnerCard) {
            binding.layoutBeginnerCard.background = ContextCompat.getDrawable(requireContext(), R.drawable.bgcard)
            binding.easyTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.black10))
        }
        if (selectedLayout != binding.layoutIntermediateCard) {
            binding.layoutIntermediateCard.background = ContextCompat.getDrawable(requireContext(), R.drawable.bgcard)
            binding.mediumTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.black10))
        }
        if (selectedLayout != binding.layoutExpertCard) {
            binding.layoutExpertCard.background = ContextCompat.getDrawable(requireContext(), R.drawable.bgcard)
            binding.expertTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.black10))
        }

        // Set the text color of the selected TextView
        selectedTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun setSelectedDifficultyForQue(selectedLayout: RelativeLayout, selectedTextView: TextView) {
        // Set the background drawable to the selected layout
        selectedLayout.background = ContextCompat.getDrawable(requireContext(), R.drawable.gredient_copy_main)

        // Reset background drawable for other layouts
        if (selectedLayout != binding.que10Layout) {
            binding.que10Layout.background = ContextCompat.getDrawable(requireContext(), R.drawable.bgcard)
            binding.que10.setTextColor(ContextCompat.getColor(requireContext(), R.color.black10))
        }
        if (selectedLayout != binding.que15Layout) {
            binding.que15Layout.background = ContextCompat.getDrawable(requireContext(), R.drawable.bgcard)
            binding.que15.setTextColor(ContextCompat.getColor(requireContext(), R.color.black10))
        }
        if (selectedLayout != binding.que25Layout) {
            binding.que25Layout.background = ContextCompat.getDrawable(requireContext(), R.drawable.bgcard)
            binding.que25.setTextColor(ContextCompat.getColor(requireContext(), R.color.black10))
        }

        // Set the text color of the selected TextView
        selectedTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
