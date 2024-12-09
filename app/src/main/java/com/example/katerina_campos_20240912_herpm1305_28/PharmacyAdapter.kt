package com.example.katerina_campos_20240912_herpm1305_28

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.katerina_campos_20240912_herpm1305_28.databinding.ItemPharmacyBinding
import com.example.katerina_campos_20240912_herpm1305_28.models.Pharmacy

class PharmacyAdapter(private val pharmacies: List<Pharmacy>) :
    RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyViewHolder {
        val binding = ItemPharmacyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PharmacyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PharmacyViewHolder, position: Int) {
        val pharmacy = pharmacies[position]
        holder.binding.localName.text = pharmacy.local_nombre
        holder.binding.localAddress.text = "${pharmacy.comuna_nombre}, ${pharmacy.localidad_nombre}"
    }

    override fun getItemCount(): Int = pharmacies.size

    class PharmacyViewHolder(val binding: ItemPharmacyBinding) : RecyclerView.ViewHolder(binding.root)
}

