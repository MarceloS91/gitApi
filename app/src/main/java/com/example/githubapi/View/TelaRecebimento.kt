package com.example.githubapi.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapi.Adapter.Adapter
import com.example.githubapi.Model.Repositorio
import com.example.githubapi.Network.EndPoint
import com.example.githubapi.Network.NetworkUtils
import com.example.githubapi.R
import com.example.githubapi.databinding.FragmentTelaRecebimentoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaRecebimento : Fragment() {

    private var _binding: FragmentTelaRecebimentoBinding? = null
    private val binding get() = _binding!!
    private val args: TelaRecebimentoArgs by navArgs()
    private lateinit var repositorioAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTelaRecebimentoBinding.inflate(inflater, container, false)
        return binding.root
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         loadView()
     }

    private fun loadView() {
        binding.textViewNome.text = args.user.nome
        getData()
    }

    private fun getData() {
        val retrofitBase = NetworkUtils.getRetrofitInstance()
        val endPointPatch = retrofitBase.create(EndPoint::class.java)
        val callback = endPointPatch.getRepos(args.user.login)

        callback.enqueue(object: Callback<List<Repositorio>> {
            override fun onFailure(call: Call<List<Repositorio>>, t: Throwable) {
                Toast.makeText(requireContext(), "Não Funfa", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<Repositorio>>,
                response: Response<List<Repositorio>>
            ) {
                response.body()?.let {
                    setAdapter(it)
                }
            }
        })
    }

    private fun setAdapter(listRepos: List<Repositorio>) {
        repositorioAdapter = Adapter(listRepos) {
            openGithub(it)
        }

        binding.recyclerView.apply {
            adapter = repositorioAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun openGithub(repository: Repositorio) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repository.htmlUrl))
        startActivity(intent)

}
}
