package com.example.myfluttermoduleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        textview_first.text = when {
            requireActivity() is MainActivity -> {
                "cur is MainActivity"
            }
            requireActivity() is SecondActivity -> {
                "cur is SecondActivity"
            }
            requireActivity() is ThreeActivity -> {
                "cur is ThreeActivity"
            }
            requireActivity() is FourActivity -> {
                "cur is FourActivity"
            }
            requireActivity() is FiveActivity -> {
                "cur is FiveActivity"
            }
            requireActivity() is SixActivity -> {
                "cur is SixActivity"
            }
            else -> {
                ""
            }
        }
    }
}