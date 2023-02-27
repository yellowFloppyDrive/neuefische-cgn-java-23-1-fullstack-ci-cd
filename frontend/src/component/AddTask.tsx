import {ChangeEvent, FormEvent, useState} from "react";
import {NewTask} from "../model/Task";
import './AddTask.css'

type Props = {
     onAdd:(newTask:NewTask)=>Promise<void>
}

export default function AddTask(props:Props){
    const [title,setTitle] = useState<string>("")
    const [dateTime,setDateTime] = useState<Date>(new Date())

    function handleTitleChange(event:ChangeEvent<HTMLInputElement>){
        setTitle(event.target.value)
    }

    function handleDateTimeChange(event:ChangeEvent<HTMLInputElement>){
        const date = new Date(event.target.value)
        setDateTime(date)
    }

    function formSubmitHandler(event:FormEvent<HTMLFormElement>){
        event.preventDefault()
        const newTask : NewTask = {
            title,dateTime
        }
        props.onAdd(newTask)
            .then(()=>{
                setTitle("")
                setDateTime(new Date())
            })
    }

    return(
        <>
        <h2>Add New Task</h2>
        <form onSubmit={formSubmitHandler} className={"add-task"}>
            <label>
                Title:
                <input type={"text"} onChange={handleTitleChange} value={title} placeholder={"Have a long break"}required={true}/>
            </label>
            <label>Please select date and time:
                <input type={"datetime-local"} onChange={handleDateTimeChange} value={dateTime.toISOString().slice(0,-5)}required={true}/>
            </label>
            <button type={"submit"}>Save</button>
        </form>
        </>
    )

}