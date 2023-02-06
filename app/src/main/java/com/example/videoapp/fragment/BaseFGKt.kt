package com.example.videoapp.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.videoapp.MyApplication
import java.lang.reflect.ParameterizedType

abstract class BaseFGKt<T : ViewBinding> : Fragment() {
//    abstract var binding: T
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        inflater.inflate(initLayout(),container,false)
//
//
//        val type = javaClass.genericSuperclass as ParameterizedType
//        val aClass = type.actualTypeArguments[0] as Class<*>
//        val method = aClass.getDeclaredMethod(
//            "inflate",
//            LayoutInflater::class.java,
//            ViewGroup::class.java,
//            Boolean::class.java
//        )
//        binding = method.invoke(null, layoutInflater, container, false) as T
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initData()
//        initListener()
//    }
//
//
//    open fun initListener() {
//
//    }
//
//    open fun initData() {
//
//    }


}