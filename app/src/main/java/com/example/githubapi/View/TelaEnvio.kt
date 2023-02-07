package com.example.githubapi.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.githubapi.Model.User
import com.example.githubapi.Network.EndPoint
import com.example.githubapi.Network.NetworkUtils
import com.example.githubapi.R
import com.example.githubapi.databinding.FragmentTelaEnvioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaEnvio : Fragment(R.layout.fragment_tela_envio) {

    private var _binding: FragmentTelaEnvioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTelaEnvioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonClicked()
    }

    private fun setButtonClicked() {
        binding.buttonEnviarDados.setOnClickListener{
            getData()
        }
    }

    private fun getData() {
        val retrofitBase = NetworkUtils.getRetrofitInstance()
        val endPointPatch = retrofitBase.create(EndPoint::class.java)
        val callback = endPointPatch.getUsers(binding.editTextTextPersonName.text.toString())

        callback.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(requireContext(), "Não funfa", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let {

                    var action = TelaEnvioDirections.actionTelaEnvioToTelaRecebimento(
                        it
                    )

                    findNavController().navigate(action)
                }
            }
        })
    }
}