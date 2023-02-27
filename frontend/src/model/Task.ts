
export type  Task={
    id: string,
    title:string,
    dateTime:Date
}

export type NewTask = Omit<Task, "id">