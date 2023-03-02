package tn.esprit.curriculumvitaev2.dao

interface GenericDao<T> {

    fun getAll(): List<T>

    fun create(t: T)

    fun getOne(id: Int): T

    fun deleteOne(t: T)
}