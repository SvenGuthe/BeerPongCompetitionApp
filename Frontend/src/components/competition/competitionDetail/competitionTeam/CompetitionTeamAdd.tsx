import React, { useRef, useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { addCompetitionTeam } from "../../../../store/competition/competition-store-actions";
import { tUserIDAndGamerTag } from "../../../../types/authentication";
import { tCompetitionTeamAdd } from "../../../../types/competition";
import { tTeamAndUser } from "../../../../types/team";
import { removeDuplicates } from "../../../../utility/arrayFunctions";

const CompetitionTeamAdd: React.FC<{
    teams: tTeamAndUser[],
    users: tUserIDAndGamerTag[],
    competitionId: number
}> = (props) => {

    const [selectedTeam, setSelectedTeam] = useState<tTeamAndUser | null>(null);
    const [selectedUsers, setSelectedUsers] = useState<tUserIDAndGamerTag[]>([]);

    const competitionTeamNameRef = useRef<HTMLInputElement>(null);
    const competitionTeamPasswordRef = useRef<HTMLInputElement>(null);

    const dispatch = useDispatch();

    const teams = props.teams;

    const onSelectChangeHandler = (event: React.FormEvent<HTMLSelectElement>) => {
        const value = event.currentTarget.value;

        const chosenTeam = teams.find(team => team.team.id === +value);

        if (chosenTeam) {
            setSelectedTeam(chosenTeam);
        } else {
            setSelectedTeam(null)
        }
    }

    const onMultiSelectChangeHandler = (event: React.FormEvent<HTMLSelectElement>) => {
        const value = event.currentTarget.selectedOptions;
        const possibleUsersOption = removeDuplicates(teams.flatMap(team => team.users));

        let selectedValues: tUserIDAndGamerTag[] = [];
        for (let i = 0; i < value.length; i++) {
            possibleUsersOption?.filter(user => user.id === +value.item(i)!.value).forEach(user => {
                selectedValues.push(user);
            });
        }
        setSelectedUsers(selectedValues);
    }

    const onAddHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        const competitionTeam: tCompetitionTeamAdd = {
            id: props.competitionId,
            teamname: competitionTeamNameRef.current!.value,
            password: competitionTeamPasswordRef.current!.value,
            teamId: selectedTeam?.team.id,
            playerIds: selectedUsers.map(user => user.id)
        }
        dispatch(addCompetitionTeam(competitionTeam));

        setSelectedTeam(null);
        setSelectedUsers([]);
    }

    let possibleTeams: tUserIDAndGamerTag[] = [];

    if (selectedTeam) {
        possibleTeams = selectedTeam.users.filter(user => props.users.find(singleUser => singleUser.id === user.id));
    } else {
        possibleTeams = props.users;
    }

    const defaultValue = {
        id: -1,
        teamName: "Ohne Hauptteam"
    }
    const selectTeamDefaults = [defaultValue].concat(teams.map(team => team.team));

    const output = <Container style={{ padding: "0px", margin: "0px" }}>
        <Form>
            <Row>
                <Form.Group as={Col} sm={2}>
                    <Form.Label htmlFor="competitionTeamName">Turnier Team Name</Form.Label>
                    <Form.Control id="competitionTeamName" type="text" placeholder="Enter Team Name" ref={competitionTeamNameRef} />
                </Form.Group>
                <Form.Group as={Col} sm={2}>
                    <Form.Label htmlFor="competitionTeamPassword">Passwort</Form.Label>
                    <Form.Control id="competitionTeamPassword" type="password" placeholder="Enter Password" ref={competitionTeamPasswordRef} />
                </Form.Group>
                <Form.Group as={Col} sm={4}>
                    <Form.Label htmlFor="competitionMainTeam">Turnier Team Name</Form.Label>
                    <Form.Select id="competitionMainTeam" onChange={onSelectChangeHandler}>
                        {selectTeamDefaults.map(selectTeamDefault => <option key={selectTeamDefault.id} value={selectTeamDefault.id}>{selectTeamDefault.teamName}</option>)}
                    </Form.Select>
                </Form.Group>
                <Form.Group as={Col} sm={2}>
                    <Form.Label htmlFor="competitionTeamUser">Nutzer</Form.Label>
                    <Form.Select id="competitionTeamUser" onChange={onMultiSelectChangeHandler} multiple>
                        {possibleTeams.map(possibleTeam => <option key={possibleTeam.id} value={possibleTeam.id}>{possibleTeam.gamerTag}</option>)}
                    </Form.Select>
                </Form.Group>
                <Col sm={2} style={{ textAlign: "right" }}>
                    <Button variant="secondary" type="submit" size="sm" onClick={onAddHandler} style={{ width: '100px' }}>
                        Add
                    </Button>
                </Col>
            </Row>
        </Form>
    </Container >

    return output;

}

export default CompetitionTeamAdd;